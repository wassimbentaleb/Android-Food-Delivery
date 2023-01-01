package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.time.format.DateTimeFormatter
import java.util.*


// POSTADAPTER is between view and data 
class PostAdapter(private val items: List<Data> ,private val context:
Context)
    :RecyclerView.Adapter<RecyclerView.ViewHolder>() , Filterable{


    // set layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.post_item, parent, false)
        return PostViewHolder(view)
    }
    var postFilterList : List<Data> = items


    // filter the posts with searchView
    override fun getFilter(): Filter {

        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                     postFilterList = items
                } else {
                    val resultList : MutableList<Data> = mutableListOf()
                    for (row in items) {
                        if (row.user?.username.lowercase(Locale.ROOT).contains(charSearch.lowercase(Locale.ROOT))) {
                            resultList.add(row)
                        }
                    }
                    postFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = postFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                Log.i("result search", results?.values.toString())
                if(results?.values !==null){
                    postFilterList = results?.values as List<Data>
                }
                notifyDataSetChanged()
            }

        }
    }


    // set Data from response to view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is PostViewHolder) {

           var item : Data
            if(getItemCount() <16){
                 item = postFilterList[position] as Data
            }else{
                item = items[position] as Data
            }
            val media = item.urls?.regular
            if(media !== null)
            {
                Glide.with(context)
                    .load(item.urls?.regular)
                    .into(holder.imgPost!!)
                holder.userPost.text = item.user?.username

                holder.datePost.text = item.created_at.toString().removeRange(11,30)
            }
            // pass from home page to profile with the necessairy data
            holder.postId.setOnClickListener(View.OnClickListener {
                val intent = Intent(context,ProfileDetails::class.java)
                intent.putExtra("username",item.user?.username)
                intent.putExtra("imageCover",item.urls?.regular)
                intent.putExtra("imageProfile",item.user?.profile_image?.small)
                intent.putExtra("bio",item.user?.bio)
                intent.putExtra("name",item.user?.name)
                intent.putExtra("createdAt",item?.created_at.toString().removeRange(11,30))
                intent.putExtra("updatedAt",item?.updated_at.toString().removeRange(11,30))

                context.startActivity(intent)




            })
        }

    }

    override fun getItemCount(): Int {
        return postFilterList.count()
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPost = itemView.findViewById<ImageView>(R.id.imagePost)
        val userPost = itemView.findViewById<TextView>(R.id.userPost)
        val datePost = itemView.findViewById<TextView>(R.id.postDate)
        val textSearch = itemView.findViewById<SearchView>(R.id.txtsearch)
        val postId = itemView.findViewById<LinearLayout>(R.id.postId)

        init {
            itemView.findViewById<View>(R.id.storyOutline)
        }
    }

}

