package com.most.popular.model

import com.google.gson.annotations.SerializedName

data class Media(
    val type: String,
    @SerializedName("media-metadata")
    val files: List<FileModel>
)