package com.example.readdletask.model

import com.google.gson.annotations.Expose

data class RandomUserResponse(
    val results: List<Result>
)


data class Result(
    val email: String,
    val name: Name
)


data class Name(
    val first: String,
    val last: String,
    val title: String
)
