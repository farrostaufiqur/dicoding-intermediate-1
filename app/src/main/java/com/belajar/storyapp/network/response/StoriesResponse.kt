package com.belajar.storyapp.network.response

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class StoriesResponse (
    @SerializedName("error")
    val error: Boolean,
    @SerializedName("message")
    val message: String,
    @SerializedName("listStory")
    val listStory: List<Story>
){
    @Parcelize
    data class Story(

        @SerializedName("id")
        val id: String,

        @SerializedName("name")
        val name: String,

        @SerializedName("description")
        val description: String,

        @SerializedName("photoUrl")
        val photoUrl: String,

        @SerializedName("createdAt")
        val createdAt: String
    ) :Parcelable
}