package com.softbankrobotics.mvvm.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Album(
    @PrimaryKey(autoGenerate = false)
    val albumId: String,
    val id: String,
    val thumbnailUrl: String? = null,
    val title: String,
    val url: String? = null
)