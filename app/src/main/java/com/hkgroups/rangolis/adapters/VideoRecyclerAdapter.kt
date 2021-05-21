package com.hkgroups.rangolis.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubeThumbnailLoader
import com.google.android.youtube.player.YouTubeThumbnailView
import com.hkgroups.rangolis.R
import com.hkgroups.rangolis.activites.VideoOnClickActivity
import com.hkgroups.rangolis.models.VideoRecyclerModel

class VideoRecyclerAdapter(var context: Context, var list :List<VideoRecyclerModel>):RecyclerView.Adapter<VideoRecyclerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun setData(itemModel:VideoRecyclerModel?){
            val thumbnailView:YouTubeThumbnailView = itemView.findViewById(R.id.videoRecyclerThumbnailView)
            thumbnailView.initialize("AIzaSyCDBVemVqJIIF3Yf2ISCKvdtKmj-r5WiD8",object :YouTubeThumbnailView.OnInitializedListener{
                override fun onInitializationSuccess(
                    p0: YouTubeThumbnailView?,
                    p1: YouTubeThumbnailLoader?
                ) {
                    p1!!.setVideo(itemModel!!.videoId)
                }

                override fun onInitializationFailure(
                    p0: YouTubeThumbnailView?,
                    p1: YouTubeInitializationResult?
                ) {

                }
            })
            thumbnailView.setOnClickListener {
                val intent = Intent(context,VideoOnClickActivity::class.java)
                intent.putExtra("videoId",itemModel!!.videoId)
                intent.putExtra("title",itemModel.title)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.video_recycler_model,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = list[position]
        holder.setData(list)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}