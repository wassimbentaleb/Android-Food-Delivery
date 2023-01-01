package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import androidx.appcompat.widget.SearchView

import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


       var dataView :  DataViewModel = DataViewModel()

        dataView.loadDatas()

        val model: DataViewModel =
            ViewModelProvider(this).get(DataViewModel::class.java)
        model.fecthRandom()?.observe(this, object : Observer<List<Data>> {

            override fun onChanged(@Nullable dataList: List<Data>?) {
                val storiesbar = findViewById<RecyclerView>(R.id.storiesBar)
                val linearLayoutManager = LinearLayoutManager(this@MainActivity)
                linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
                storiesbar.layoutManager = linearLayoutManager
                var dataAdapter = DataAdapter(dataList!!,this@MainActivity)
                storiesbar.addItemDecoration(StoriesDecoration(10))
                storiesbar.adapter = dataAdapter
                val postList = findViewById<RecyclerView>(R.id.postList)
                val linearLayoutManager2 = GridLayoutManager(this@MainActivity,2)
                postList.layoutManager = linearLayoutManager2
                var postAdapter = PostAdapter(dataList!!,this@MainActivity)
                postList.adapter = postAdapter
                val txtSearch = findViewById<SearchView>(R.id.txtsearch)
                txtSearch.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        postAdapter.filter.filter(newText)
                        return false
                    }

                })
            }







        })


    }



}