package com.hkgroups.rangolis.models

class MainModel {
    var type: Int = 0
    var title: String = ""

    constructor() {}
    constructor(type: Int, title: String) {
        this.type = type
        this.title = title
    }
}