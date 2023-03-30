package com.example.profile_list_mvvm

import com.squareup.moshi.Json

data class Charakter(
    @Json(name = "name")
    val name: String,
    @Json(name = "image")
    val image: String

)

data class CharakterResponse(
    @Json(name = "result")
    val result: List<Charakter>
)