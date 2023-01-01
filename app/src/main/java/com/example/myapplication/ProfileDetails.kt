package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

import com.bumptech.glide.Glide

class ProfileDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_details)

        val username:String = intent.getStringExtra("username").toString()
        val name:String = intent.getStringExtra("name").toString()
        val bio:String = intent.getStringExtra("bio").toString()
        val dateCreation:String = intent.getStringExtra("createdAt").toString()
        val dateUp:String = intent.getStringExtra("updatedAt").toString()
        val imageCover = intent.getStringExtra("imageCover")
        val imageprofile = intent.getStringExtra("imageProfile")
        val viewUser = findViewById<TextView>(R.id.usernameTxt)
        val viewDescSmall = findViewById<TextView>(R.id.smallDesc)
        val viewDate = findViewById<TextView>(R.id.dateCrt)
        val viewDateUp = findViewById<TextView>(R.id.dateUpd)
        val viewDesc = findViewById<TextView>(R.id.desc)
        val imageViewC = findViewById<ImageView>(R.id.coverImage)
        val imageViewP = findViewById<ImageView>(R.id.profileImage)
        viewUser.text = username
        viewDescSmall.text = name
        viewDate.text = "Created at : "+ dateCreation
        viewDateUp.text = "Updated at : "+ dateUp
        viewDesc.text = bio
        Glide.with(this)
            .load(imageCover)
            .into(imageViewC)
        Glide.with(this)
            .load(imageprofile)
            .into(imageViewP)

    }
}