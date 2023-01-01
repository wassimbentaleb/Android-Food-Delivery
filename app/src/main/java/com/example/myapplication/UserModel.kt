package com.example.myapplication

import java.util.*


// user model
data class UserModel  (
    val id : String,
    val username : String,
    val bio : String ,
    val name : String,
    val profile_image: ImageModel,
)