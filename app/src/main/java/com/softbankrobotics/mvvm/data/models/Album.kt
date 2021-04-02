package com.softbankrobotics.mvvm.data.models

data class Album(
    val albumId: String,
    val id: String,
    val thumbnailUrl: String? = null,
    val title: String,
    val url: String? = null
)