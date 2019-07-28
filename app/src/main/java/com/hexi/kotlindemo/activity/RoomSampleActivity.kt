package com.hexi.kotlindemo.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.hexi.kotlindemo.R
import com.hexi.kotlindemo.adapter.UserAdapter
import com.hexi.kotlindemo.persistence.User
import com.hexi.kotlindemo.persistence.UserDao
import com.hexi.kotlindemo.persistence.UsersDatabase
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.android.synthetic.main.activity_room_sample.*

class RoomSampleActivity : FragmentActivity() {

    private var userDisposable: DisposableSubscriber<List<User>>? = null
    private val TAG = "RoomSampleActivity"

    private lateinit var adapter: UserAdapter
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_sample)
        this.userDao = UsersDatabase.getInstance(this).userDao()
        this.adapter = UserAdapter()
        initView()
    }

    private fun initView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        loadAllUsers()
    }

    private fun loadAllUsers() {
        this.userDisposable?.dispose()
        this.userDisposable = userDao.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSubscriber<List<User>>() {
                    override fun onComplete() {
                        Log.d(TAG, "===loadAllUsers.onComplete")
                    }

                    override fun onNext(t: List<User>) {
                        Log.d(TAG, "===loadAllUsers.onNext: ${Gson().toJson(t)}")
                        adapter.setData(t)
                    }

                    override fun onError(t: Throwable?) {
                        Log.d(TAG, "===loadAllUsers.onError: ", t)
                    }

                })
    }

    fun insertUser(view: View) {
        val username = et_username.text.toString()
        val user = User(userName = username)
        Single.fromCallable {
            userDao.insertUser(user)
        }.subscribeOn(Schedulers.io()).subscribe({ id ->
            Log.d(TAG, "===insertUser.onSuccess, id: $id")
        }, {
            Log.e(TAG, "===insertUser.onError: ", it)
        })
    }

    fun queryUsers(view: View) {
        loadAllUsers()
    }

    fun deleteAllUser(view: View) {
        Single.fromCallable {
            userDao.deleteAllUsers()
        }.subscribeOn(Schedulers.io()).subscribe({ count ->
            Log.d(TAG, "===deleteAllUsers.onSuccess, count: $count")
        }, {
            Log.e(TAG, "===insertUser.onError: ", it)
        })
    }
}