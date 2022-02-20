package com.example.youtubeui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeui.R
import com.example.youtubeui.model.Filter

class FilterAdapter(private val context: Context, private val items: ArrayList<Filter>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.item_feed_filter, parent, false)
        return FilterViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val filter = items[position]

        if (holder is FilterViewHolder) {
            val title = (holder as FilterViewHolder).title

            title.text = filter.title
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    private class FilterViewHolder(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        val title: TextView = myItemView.findViewById(R.id.tv_title_capsule)
    }
}

