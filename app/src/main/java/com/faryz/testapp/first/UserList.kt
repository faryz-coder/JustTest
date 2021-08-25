package com.faryz.testapp.first

import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.faryz.testapp.R

class UserList(private val userList: MutableList<ListUser>) :
    RecyclerView.Adapter<UserList.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.user_list_name)
        val row: ConstraintLayout = itemView.findViewById(R.id.user_list_row)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val loc = userList[position]
        holder.name.text = "${loc.firstName} ${loc.lastName}"
        d("bomoh", "name ${loc.firstName}")
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}
