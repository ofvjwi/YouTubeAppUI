package com.example.youtubeui.activity

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeui.R
import com.example.youtubeui.adapter.FeedAdapter
import com.example.youtubeui.adapter.FilterAdapter
import com.example.youtubeui.model.Feed
import com.example.youtubeui.model.Filter
import android.widget.Toast

import android.net.ConnectivityManager
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerViewFilter: RecyclerView
    private lateinit var recyclerViewFeed: RecyclerView
    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!
                .isConnected
        ) {
            Toast.makeText(this@MainActivity, "Internet Available", Toast.LENGTH_SHORT).show()
        } else {

            MaterialAlertDialogBuilder(context)
                .setMessage("Please connect to the internet!")
                .setPositiveButton("Ok") { dialog, which ->
                    // Respond to positive button press

                }
                .setNegativeButton("No") { dialog, which ->
                    // Respond to positive button press
                    Toast.makeText(this, "Goodbye!", Toast.LENGTH_SHORT).show()
                    finishAffinity()
                }
                .show()

        }
    }

    private fun initViews() {
        context = this

        recyclerViewFeed = findViewById(R.id.recycler_view_feed)
        recyclerViewFeed.layoutManager =
            GridLayoutManager(context, 1)

        recyclerViewFilter = findViewById(R.id.recycler_view_filter)
        recyclerViewFilter.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        refreshStoryAdapter(getAllFilters())
        refreshFeedAdapter(getAllFeeds())
    }

    private fun refreshStoryAdapter(items: ArrayList<Filter>) {
        val adapter = FilterAdapter(context, items)
        recyclerViewFilter.adapter = adapter
    }

    private fun refreshFeedAdapter(items: ArrayList<Feed>) {
        val adapter = FeedAdapter(context, items, lifecycle)
        recyclerViewFeed.adapter = adapter
    }

    private fun getAllFilters(): ArrayList<Filter> {
        val list = ArrayList<Filter>()

        list.add(Filter("Computer"))
        list.add(Filter("Cartoon"))
        list.add(Filter("Media"))
        list.add(Filter("Car"))
        list.add(Filter("Android"))
        list.add(Filter("Horror films"))

        list.shuffle()

        return list
    }

    private fun getAllFeeds(): ArrayList<Feed> {
        val list = ArrayList<Feed>()

        for (i in 1..30) list.add(Feed(R.drawable.im_user_profile, R.drawable.im_video))

        list.shuffle()
        return list
    }
}

