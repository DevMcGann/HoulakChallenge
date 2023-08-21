package com.gsoft.hourakchallenge.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArtistResponse(
    val id : String,
    val name : String,
    val popularity : Int,
    val uri : String,
    val genres : List<String>,
    val images : List<String>
)  : Parcelable

