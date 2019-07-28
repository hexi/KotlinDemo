package com.hexi.kotlindemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hexi.kotlindemo.R
import com.hexi.kotlindemo.persistence.User
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_list_user.*

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private val data: MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(users: List<User>) {
        this.data.clear()
        this.data.addAll(users)
        notifyDataSetChanged()
    }

    class UserViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bind(user: User) {
            tv_username.text = user.userName
            tv_user_id.text = user.id.toString()
        }

    }
}