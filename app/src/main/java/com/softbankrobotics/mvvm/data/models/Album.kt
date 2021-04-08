package com.softbankrobotics.mvvm.data.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class Album(
    @PrimaryKey(autoGenerate = false)
    val albumId: String,
    val id: String,
    val thumbnailUrl: String? = null,
    val title: String,
    val url: String? = null
) : Parcelable