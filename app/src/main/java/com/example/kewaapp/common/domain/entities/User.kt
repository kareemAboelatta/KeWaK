package com.example.kewaapp.common.domain.entities

data class User(
    val id:String = "",
    val firstName: String ="",
    val lastName: String="",
    val email:String="",
    val followers:Int = 0
)
