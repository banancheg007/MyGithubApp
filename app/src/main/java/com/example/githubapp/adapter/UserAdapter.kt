package com.example.githubapp.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubapp.Constants
import com.example.githubapp.R
import kotlinx.android.synthetic.main.adapter_item.view.*

class UserAdapter(private val callback: OnItemClick) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val userList: List<String> = Constants.studentList

    interface OnItemClick {
        fun onUserClicked(position: Int)
    }

    override fun getItemCount(): Int = userList.size

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.user_name.text = userList[position]
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            callback.onUserClicked(adapterPosition)
        }

    }

}