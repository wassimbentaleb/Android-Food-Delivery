package com.example.myapplication

import java.util.*

//Model data
data class Data(

    val id : String,
    val created_at : Date,
    val updated_at : Date,
    val username : String,
    val profile_image: String,
    val image : String,
    val user: UserModel,
    val urls : UrlsModel,
    var seen : Boolean = false,


    )



