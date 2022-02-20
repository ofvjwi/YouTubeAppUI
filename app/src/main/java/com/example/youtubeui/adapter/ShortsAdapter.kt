package com.example.youtubeui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeui.R


class ShortsAdapter(
    private val context: Context,
    private val items: ArrayList<Int>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TAG: String = "ShortsAdapter"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.item_shorts_rv_item, parent, false)

        return ShortsViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemView = items[position]

        if (holder is ShortsViewHolder) {
            Log.d(TAG, "onBindViewHolder: ")

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private class ShortsViewHolder(myItemView: View) : RecyclerView.ViewHolder(myItemView)
}
