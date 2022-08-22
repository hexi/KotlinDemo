package com.hexi.kotlindemo.screenRecord

import android.app.Service
import android.content.Intent
import android.hardware.display.DisplayManager
import android.hardware.display.VirtualDisplay
import android.media.MediaRecorder
import android.media.projection.MediaProjection
import android.media.projection.MediaProjectionManager
import android.os.Environment
import android.os.IBinder
import android.util.Log
import com.hexi.kotlindemo.screenRecord.ScreenRecordNotification.Companion.getInstance
import org.joda.time.DateTime
import java.io.File
import java.io.IOException

class ScreenRecordService : Service() {
    companion object {
        private const val TAG = "ScreenRecordService"

        // 开始录屏
        const val ACTION_START_RECORD = "ACTION_START_RECORD"

        // 停止录屏
        const val ACTION_STOP_RECORD = "ACTION_STOP_RECORD"

        // 子目录
        private const val SUB_DIR = "screenRecords"
        private const val VIDEO_SUFFIX = ".mp4"
    }

    /**
     * 是否为标清视频
     */
    private val isVideoSd = false
    private var mScreenWidth = 0
    private var mScreenHeight = 0
    private var mScreenDensity = 0
    private var mResultCode = 0
    private var mResultData: Intent? = null
    private var mMediaProjection: MediaProjection? = null
    private var mMediaRecorder: MediaRecorder? = null
    private var mVirtualDisplay: VirtualDisplay? = null
    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "===onCreate")
        getInstance().init(this)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val action = intent.action
        Log.d(TAG, "===onStartCommand, action: $action")
        when (action) {
            ACTION_START_RECORD -> {
                mResultCode = intent.getIntExtra("resultCode", 1)
                mResultData = intent.getParcelableExtra("data")
                screenBaseInfo
                mMediaProjection = createMediaProjection()
                mMediaRecorder = createMediaRecorder()
                mVirtualDisplay =
                    createVirtualDisplay() // 必须在mediaRecorder.prepare() 之后调用，否则报错"fail to get surface"
                mMediaRecorder?.start()
            }
            ACTION_STOP_RECORD -> {
                stopSelf()
            }
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "===onDestroy")
        getInstance().stopForeground()
        mVirtualDisplay?.release()
        mVirtualDisplay = null
        mMediaRecorder?.setOnErrorListener(null)
        mMediaRecorder?.reset()
        mMediaRecorder = null
        mMediaProjection?.stop()
        mMediaProjection = null
    }
    /**
     * 获取屏幕相关数据
     */
    /**
     * 获取屏幕相关数据
     */
    private val screenBaseInfo: Unit
        private get() {
            mScreenWidth = ScreenUtils.getScreenWidth(this)
            mScreenHeight = ScreenUtils.getScreenHeight(this)
            mScreenDensity = ScreenUtils.getScreenDensityDpi(this)
        }

    private fun createMediaProjection(): MediaProjection {
        Log.i(TAG, "Create MediaProjection")
        return (getSystemService(MEDIA_PROJECTION_SERVICE) as MediaProjectionManager).getMediaProjection(
            mResultCode,
            mResultData!!
        )
    }

    private fun createMediaRecorder(): MediaRecorder? {
        var videoQuality = "HD"
        if (isVideoSd) videoQuality = "SD"
        Log.i(TAG, "Create MediaRecorder")
        val mediaRecorder = MediaRecorder()
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
        mediaRecorder.setVideoSource(MediaRecorder.VideoSource.SURFACE)
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB) //after setOutputFormat()
        mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264) //after setOutputFormat()
        mediaRecorder.setVideoSize(
            mScreenWidth,
            mScreenHeight
        ) //after setVideoSource(), setOutFormat()

        var recordFile: File = getOutputFile(videoQuality) ?: return null

        Log.d(TAG, "===start record, fileName: ${recordFile.absolutePath}")
        mediaRecorder.setOutputFile(recordFile.absolutePath)
        val bitRate: Int = if (isVideoSd) {
            mediaRecorder.setVideoEncodingBitRate(mScreenWidth * mScreenHeight)
            mediaRecorder.setVideoFrameRate(30)
            mScreenWidth * mScreenHeight / 1000
        } else {
            mediaRecorder.setVideoEncodingBitRate(5 * mScreenWidth * mScreenHeight)
            mediaRecorder.setVideoFrameRate(60) //after setVideoSource(), setOutFormat()
            5 * mScreenWidth * mScreenHeight / 1000
        }
        try {
            mediaRecorder.prepare()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return mediaRecorder
    }

    private fun getOutputFile(videoQuality: String): File? {
        val cacheDir = getCacheFolder() ?: return null
        val timestamp = DateTime.now().toString("yyyy-MM-dd-HH-mm-ss")
        val fileName = "$videoQuality-$timestamp$VIDEO_SUFFIX"
        return try {
            File(cacheDir.absolutePath + File.separator + fileName)
        } catch (e: Exception) {
            Log.e(TAG, "缓存文件创建失败", e)
            null
        }
    }

    private fun getCacheFolder(): File? {
        val path = getCachePath()
        if (path.isNullOrBlank()) {
            return null
        }
        return try {
            val ret = File(path)
            if (!ret.exists()) {
                ret.mkdirs()
            }
            ret
        } catch (e: Exception) {
            Log.e(TAG, "缓存目录创建失败", e)
            null
        }
    }

    private fun getCachePath(): String? {
        try {
            val context = applicationContext
            var cacheDir: File? = null
            if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
                cacheDir = context.externalCacheDir
            }
            if (cacheDir == null) {
                cacheDir = context.cacheDir
            }
            if (cacheDir == null) {
                return null
            }
            return cacheDir.absolutePath + File.separator + SUB_DIR
        } catch (e: Exception) {
            Log.e(TAG, "===get cache path error", e)
            return null
        }
    }

    private fun createVirtualDisplay(): VirtualDisplay? {
        Log.d(TAG, "===Create VirtualDisplay")
        val mediaRecorder = this.mMediaRecorder ?: return null
        return mMediaProjection!!.createVirtualDisplay(
            TAG,
            mScreenWidth,
            mScreenHeight,
            mScreenDensity,
            DisplayManager.VIRTUAL_DISPLAY_FLAG_AUTO_MIRROR,
            mediaRecorder.surface,
            null,
            null,
        )
    }
}