package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DataAdapter(private val items: List<Data> ,private val context:
        Context)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.story_item, parent, false)
        return StoriesViewHolder(view)
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {



        if (holder is StoriesViewHolder) {

            Toast.makeText(context, "seen",Toast.LENGTH_LONG)
            val item = items[position] as Data
            val media = item.user?.profile_image?.small
            if(media !== null)
            {
                Glide.with(context)
                    .load(item.user?.profile_image?.small)
                    .into(holder.imgData!!)
                holder.txtDataname.text = item.user?.username.take(8)
            }


            val msg = holder.txtDataname.text

            holder.storyId.setOnClickListener(View.OnClickListener {
                val intent = Intent(context,StoryDetails::class.java)
                intent.putExtra("user",msg)
                intent.putExtra("image",item.urls?.regular)
                context.startActivity(intent)
                item.seen = true
                if (item.seen) {
                    holder.storyOut.setCardBackgroundColor(Color.GRAY)

                }

            })

        }

    }

    override fun getItemCount(): Int {
        return items.count()
    }

    class StoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgData = itemView.findViewById<ImageView>(R.id.imageStory)
        val storyId = itemView.findViewById<LinearLayout>(R.id.storyCard)
        val txtDataname = itemView.findViewById<TextView>(R.id.txtStory)
        val storyOut = itemView.findViewById<CardView>(R.id.storyOutline)
        init {
            itemView.findViewById<View>(R.id.storyOutline)
        }
    }

}

