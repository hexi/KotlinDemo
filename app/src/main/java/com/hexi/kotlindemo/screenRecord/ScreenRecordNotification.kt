package com.hexi.kotlindemo.screenRecord

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.RemoteViews
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.hexi.kotlindemo.R

class ScreenRecordNotification private constructor() {
    private var playerService: ScreenRecordService? = null
    private var notificationManager: NotificationManager? = null
    private var isDark = false

    private object SingletonHolder {
        val instance = ScreenRecordNotification()
    }

    fun init(playerService: ScreenRecordService) {
        this.playerService = playerService
        notificationManager = playerService.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        Log.d(TAG, "===init, notificationManager: $notificationManager")
        // 前台服务
        isDark = isDarkNotificationBar(playerService)
        playerService.startForeground(NOTIFICATION_ID, buildNotification(playerService))
    }

    fun stopForeground() {
        playerService?.stopForeground(true)
        playerService = null
        notificationManager = null
    }

    fun updateNotification() {
        Log.d(TAG, "===updateNotification")
        val service = this.playerService ?: return
        notificationManager?.notify(NOTIFICATION_ID, buildNotification(service))
    }

    private fun buildNotification(service: ScreenRecordService): Notification {
        Log.d(TAG, "===buildNotification")
        val resultIntent = Intent(service, ScreenRecordService::class.java)
//        resultIntent.action = ScreenRecordService.ACTION_CLICK_NOTIFICATION
        val pendingIntent: PendingIntent = PendingIntent.getService(service, System.currentTimeMillis().toInt(), resultIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        //适配安卓8.0的消息渠道
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(false)
            channel.enableVibration(false)
            notificationManager?.createNotificationChannel(channel)
        }
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(service, CHANNEL_ID)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContent(getRemoteViews(service))
        return builder.build()
    }

    private fun getRemoteViews(context: Context): RemoteViews {
        val layoutId: Int = if (isDark) R.layout.audio_notification_dark else R.layout.audio_notification_light
        val remoteViews = RemoteViews(context.packageName, layoutId)

        val closeIntent = Intent(context, ScreenRecordService::class.java)
        closeIntent.action = ScreenRecordService.ACTION_STOP_RECORD
        val closePendingIntent: PendingIntent = PendingIntent.getService(context, 0, closeIntent, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteViews.setOnClickPendingIntent(R.id.tv_close, closePendingIntent)

        return remoteViews
    }

    private val DUMMY_TITLE = "DUMMY_TITLE"
    private var titleColor = 0
    /**
     * 判断是否Notification背景是否为黑色
     *
     * @param context
     * @return
     */
    private fun isDarkNotificationBar(context: Context): Boolean {
        return !isColorSimilar(Color.BLACK, getNotificationTitleColor(context))
    }

    /**
     * 获取Notification 标题的颜色
     *
     * @param context
     * @return
     */
    private fun getNotificationTitleColor(context: Context): Int {
        var color = 0
        color = if (context is AppCompatActivity) {
            getNotificationColorCompat(context)
        } else {
            getNotificationColorInternal(context)
        }
        return color
    }

    /**
     * 判断颜色是否相似
     *
     * @param baseColor
     * @param color
     * @return
     */
    private fun isColorSimilar(baseColor: Int, color: Int): Boolean {
        val simpleBaseColor = baseColor or -0x1000000
        val simpleColor = color or -0x1000000
        val baseRed: Int = Color.red(simpleBaseColor) - Color.red(simpleColor)
        val baseGreen: Int = Color.green(simpleBaseColor) - Color.green(simpleColor)
        val baseBlue: Int = Color.blue(simpleBaseColor) - Color.blue(simpleColor)
        val value = Math.sqrt(baseRed * baseRed + baseGreen * baseGreen + (baseBlue * baseBlue).toDouble())
        return value < COLOR_THRESHOLD
    }

    /**
     * 获取标题颜色
     *
     * @param context
     * @return
     */
    private fun getNotificationColorInternal(context: Context): Int {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID_DEFAULT)
        builder.setContentTitle(DUMMY_TITLE)
        val notification: Notification = builder.build()
        val contentView: RemoteViews? = getContentView(context, notification)
        return if (contentView != null) {
            val notificationRoot: ViewGroup = contentView.apply(context, FrameLayout(context)) as ViewGroup
            val title: TextView? = notificationRoot.findViewById(R.id.title) as? TextView
            if (title == null) { //如果ROM厂商更改了默认的id
                iteratorView(notificationRoot, object : Filter {
                    override fun filter(view: View) {
                        if (view is TextView) {
                            val textView: TextView = view
                            if (DUMMY_TITLE == textView.text.toString()) {
                                titleColor = textView.currentTextColor
                            }
                        }
                    }
                })
                if (titleColor == 0) Color.WHITE else titleColor
            } else {
                title.currentTextColor
            }
        } else {
            Color.BLACK
        }
    }

    private fun getNotificationColorCompat(context: Context): Int {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(context)
        val notification: Notification = builder.build()
        val layoutId: Int = getContentView(context, notification)?.layoutId ?: return Color.BLACK
        val notificationRoot: ViewGroup = LayoutInflater.from(context).inflate(layoutId, null) as ViewGroup
        val title: TextView = notificationRoot.findViewById(R.id.title) as TextView
        return if (title == null) {
            val textViews: MutableList<TextView> = mutableListOf()
            iteratorView(notificationRoot, object : Filter {
                override fun filter(view: View) {
                    if (view is TextView) {
                        textViews.add(view)
                    }
                }
            })
            var minTextSize = Int.MIN_VALUE.toFloat()
            var index = 0
            var i = 0
            val j: Int = textViews.size
            while (i < j) {
                val currentSize: Float = textViews[i].textSize
                if (currentSize > minTextSize) {
                    minTextSize = currentSize
                    index = i
                }
                i++
            }
            textViews[index].text = DUMMY_TITLE
            textViews[index].currentTextColor
        } else {
            title.currentTextColor
        }
    }

    private fun getContentView(context: Context, notification: Notification): RemoteViews? {
        return if (notification.contentView != null) {
            notification.contentView
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Notification.Builder.recoverBuilder(context, notification).createContentView()
        } else {
            null
        }
    }


    private fun iteratorView(view: View?, filter: Filter?) {
        if (view == null || filter == null) {
            return
        }
        filter.filter(view)
        if (view is ViewGroup) {
            val container: ViewGroup = view
            var i = 0
            val j: Int = container.childCount
            while (i < j) {
                val child: View = container.getChildAt(i)
                iteratorView(child, filter)
                i++
            }
        }
    }

    internal interface Filter {
        fun filter(view: View)
    }

    companion object {
        private val TAG = "Notifier"
        const val CHANNEL_ID = "channel_id_screen_record"
        const val CHANNEL_NAME = "channel_name_screen_record"
        const val CHANNEL_ID_DEFAULT = "channel_id_default"
        private const val NOTIFICATION_ID = 2000
        private const val COLOR_THRESHOLD = 180.0
        @JvmStatic
        fun getInstance(): ScreenRecordNotification {
            return SingletonHolder.instance
        }
    }
}