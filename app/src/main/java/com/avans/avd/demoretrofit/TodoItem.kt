package com.avans.avd.demoretrofit

import com.squareup.moshi.Json

data class TodoItem(

    @Json(name = "userId")
    val userId: Int,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "title")
    val title: String,

    @Json(name = "completed")
    val completed: Boolean,

    )