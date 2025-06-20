package com.example.kulinaria.recyclerview
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kulinaria.R
import com.example.kulinaria.models.Recepti
import com.squareup.picasso.Picasso

class KulinariRecViewAdapter(
    private val recyclerViewInterface: KulinariRecViewInt,
    private val context: Context,
    private val destinationModels: ArrayList<Recepti>
): RecyclerView.Adapter<KulinariRecViewAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.layout_front, parent, false)
        return MyViewHolder(view, recyclerViewInterface)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val recepti = destinationModels[position]
        holder.titleTextView.text = recepti.saxeli



        Picasso.get()
            .load(recepti.imageUrl)
            .resize(300, 300)
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_background)
            .into(holder.imageViewRecepti)


        holder.bind(recepti)
    }

    override fun getItemCount(): Int {
        return destinationModels.size
    }

    class MyViewHolder(
        itemView: View,
        private val recyclerInterface: KulinariRecViewInt
    ) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textView4)
        val infoTextView: TextView = itemView.findViewById(R.id.textView5)
        val imageViewRecepti = itemView.findViewById<ImageView>(R.id.imageView2)
        fun bind(recepti: Recepti) {
            itemView.setOnClickListener {
                recyclerInterface.onClick(recepti)
            }
        }
    }

}
