package com.example.youtubeui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.youtubeui.R
import com.example.youtubeui.model.Feed
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

class FeedAdapter(private val context: Context, private val items: ArrayList<Feed>, private val lifecycle: Lifecycle) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val ITEM_SHORTS: Int = 0
        private const val ITEM_SIMPLE: Int = 1
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 1) ITEM_SHORTS else ITEM_SIMPLE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == ITEM_SIMPLE) {
            val layoutInflater =
                LayoutInflater.from(context).inflate(R.layout.item_feed_video, parent, false)
            return FeedViewHolder(layoutInflater)
        }

        val layoutInflater =
            LayoutInflater.from(context).inflate(R.layout.item_shorts_recycler_view, parent, false)
        return ShortsViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val feed = items[position]

        if (holder is FeedViewHolder) {
            val profile = (holder as FeedViewHolder).imageViewProfile
            val video = (holder as FeedViewHolder).imageViewVideo

            profile.setImageResource(feed.profile)
            video.setImageResource(feed.photo)

            val youTubePlayerView: YouTubePlayerView =
                (holder as FeedViewHolder).youTubePlayerView
            lifecycle.addObserver(youTubePlayerView)

            youTubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = "liJVSwOiiwg"
                    youTubePlayer.loadVideo(videoId, 0f)
                    youTubePlayer.pause()
                }
            })


        } else if (holder is ShortsViewHolder) {
            val recyclerView = (holder as ShortsViewHolder).recyclerView
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            refreshAdapter(recyclerView, prepareShorts())
        }

    }

    private fun refreshAdapter(recyclerView: RecyclerView, items: ArrayList<Int>) {
        val adapter = ShortsAdapter(context, items)
        recyclerView.adapter = adapter
    }

    private fun prepareShorts(): ArrayList<Int> {
        val list = ArrayList<Int>()

        for (i in 1..20) {
            list.add(R.layout.item_shorts_rv_item)
        }
        return list
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private class FeedViewHolder(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        val imageViewProfile: ImageView = myItemView.findViewById(R.id.iv_profile_item)
        val imageViewVideo: ImageView = myItemView.findViewById(R.id.iv_video)
        val youTubePlayerView: YouTubePlayerView =
            myItemView.findViewById(R.id.youtube_player_view)
    }

    private class ShortsViewHolder(myItemView: View) : RecyclerView.ViewHolder(myItemView) {
        val recyclerView: RecyclerView = myItemView.findViewById(R.id.recycler_view_shorts)
    }
}

