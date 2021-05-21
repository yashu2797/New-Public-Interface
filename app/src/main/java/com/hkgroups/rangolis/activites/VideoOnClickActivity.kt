package com.hkgroups.rangolis.activites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.hkgroups.rangolis.R
import com.hkgroups.rangolis.adapters.VideoRecyclerAdapter
import com.hkgroups.rangolis.models.VideoRecyclerModel

class VideoOnClickActivity : YouTubeBaseActivity() {
    var dataList:MutableList<VideoRecyclerModel>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_on_click)

        val youtubePlayer:YouTubePlayerView = findViewById(R.id.videoYoutubePlayer)
        val videoTitle :TextView = findViewById(R.id.videoYoutubeTitle)
        val title = intent.extras?.getString("title")
        val videoId = intent.extras?.getString("videoId")
        youtubePlayer.initialize("AIzaSyCDBVemVqJIIF3Yf2ISCKvdtKmj-r5WiD8",object :YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1!!.loadVideo(videoId)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {

            }
        })
        videoTitle.text  = title
        val recyclerView:RecyclerView = findViewById(R.id.videoOnclickRecyclerView)
        dataList = ArrayList()
        val adapter = VideoRecyclerAdapter(this,dataList!!)
        val database = Firebase.database
        val reference = database.reference
        reference.child(title!!).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onCancelled(error: DatabaseError) {

            }

            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children){
                    dataList!!.add(data.getValue(VideoRecyclerModel::class.java)!!)
                }
                adapter.notifyDataSetChanged()
            }
        })
        recyclerView.adapter = adapter
    }
}