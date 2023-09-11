package com.example.kewaapp.domain.entities

data class User(
    val id:String = "",
    val firstName: String ="",
    val lastName: String="",
    val email:String="",
    val followers:Int = 0
)
