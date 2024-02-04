package ua.ma.superheroes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class RecyclerViewAdapter(val items:Array<Hero>, val onClick:(String)->Unit):RecyclerView.Adapter<RecyclerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val listItemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_layout, parent, false)
        return RecyclerViewHolder(listItemView)
    }

    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.title.text = items[position].name
        Glide.with(holder.itemView.context)
            .load(items[position].images.md)
            .into(holder.image)
        holder.itemView.setOnClickListener{onClick(items[position].name)}
    }

}

class RecyclerViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
    val title: TextView = itemView.findViewById(R.id.ListTitle)
    val image: ImageView = itemView.findViewById(R.id.listImage)
}