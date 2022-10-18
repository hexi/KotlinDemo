package com.hexi.kotlindemo.activity

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hexi.kotlindemo.R
import com.hexi.kotlindemo.api.ApiFactory
import com.hexi.kotlindemo.coroutineSupport.LifecycleCoroutineScope
import com.hexi.kotlindemo.data.Contributor
import com.hexi.kotlindemo.ktx.showToast
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableOnSubscribe
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.activity_simple_retrofit.*
import kotlinx.android.synthetic.main.item_recycler_view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.rx2.await
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean
import java.util.concurrent.atomic.AtomicInteger

class SimpleRetrofitActivity : FragmentActivity() {
    private val TAG = "SimpleRetrofitActivity"
    private var adapter: MyAdapter? = null
    private val activityScope = LifecycleCoroutineScope()
    private val counter = AtomicInteger(0)
    private val start = AtomicBoolean(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_retrofit)
        initView()
        hideSoftKeyboard()
    }

    private fun initView() {
        container.setOnClickListener { hideSoftKeyboard() }
        tv_back.setOnClickListener { finish() }
        adapter = MyAdapter()
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = adapter

        activityScope.launch {
            flowableRequest().asFlow().collectLatest { Log.d(TAG, "===int flow: $it") }
        }
        tv_collect_flowable.setOnClickListener {
            start.set(!start.get())
        }
    }

    private fun flowableRequest(): Flowable<Int> {
        return Flowable.create(FlowableOnSubscribe<Int> { emitter ->
            while (!emitter.isCancelled) {
                Log.d(TAG, "loop in flowable")
                if (start.get()) {
                    emitter.onNext(counter.getAndAdd(1))
                }
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    Log.e(TAG, "===the flowable has canceled")
                }
            }
        }, BackpressureStrategy.BUFFER)
            .subscribeOn(Schedulers.io())
    }

    override fun onDestroy() {
        super.onDestroy()
        activityScope.close()
    }

    fun onClickSearch(view: View) {
        hideSoftKeyboard()
        loadData()
    }

    /**
     * Hide soft keyboard.
     */
    private fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val mgr = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mgr.hideSoftInputFromWindow(
                currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    private fun loadData() {
        activityScope.launch {
            val owner = et_owner.text.toString()
            val repo = et_repo.text.toString()
            if (owner.isNullOrEmpty() || repo.isNullOrEmpty()) {
                showToast("Owner and repo must not be empty!")
                return@launch
            }
            progress_bar.visibility = View.VISIBLE
            val data = fetchContributors(owner, repo)
            adapter?.setData(data)
            progress_bar.visibility = View.GONE
        }
    }

    private suspend fun fetchContributors(owner: String, repo: String): List<Contributor> {
        return withContext(Dispatchers.IO) {
            ApiFactory.githubApi
                .contributors(owner, repo)
                .await()
        }
    }
}

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val mData: MutableList<Contributor> = mutableListOf()

    fun setData(data: List<Contributor>) {
        this.mData.clear()
        this.mData.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_view, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    class MyViewHolder(override var containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(contributor: Contributor) {
            text_view.text = contributor.login
        }

    }
}