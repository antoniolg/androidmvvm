package com.antonioleiva.androidmvvm.main

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.antonioleiva.androidmvvm.R

class MainAdapter(private val items: List<String>, private val listener: (String) -> Unit) :
    androidx.recyclerview.widget.RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_main_item, parent, false) as TextView

        return MainViewHolder(v)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val item = items[position]
        holder.textView.text = item
        holder.textView.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int = items.size

    class MainViewHolder(val textView: TextView) : androidx.recyclerview.widget.RecyclerView.ViewHolder(textView) {

    }
}