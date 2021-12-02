package com.avans.avd.demoretrofit

import com.squareup.moshi.Json


data class TodoItem(

    val userId: Int,

    val id: Int? = null,

    val title: String,

    val completed: Boolean,

    )