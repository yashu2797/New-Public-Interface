package com.hkgroups.rangolis.models

class VideoRecyclerModel {
    var videoId:String = ""
    var title:String = ""
    var tag:String = ""

    constructor(){}

    constructor(videoId: String, title: String, tag: String) {
        this.videoId = videoId
        this.title = title
        this.tag = tag
    }


}