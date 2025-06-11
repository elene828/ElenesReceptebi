package com.example.kulinaria.recyclerview
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kulinaria.R
import com.example.kulinaria.models.recepti

class kulinarirecviewadapter(
    private val recyclerViewInterface: kulinarirecviewint,
    private val context: Context,
    private val destinationModels: ArrayList<recepti>
): RecyclerView.Adapter<kulinarirecviewadapter.MyViewHolder>() {
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
        holder.titleTextView.text = recepti.title
        //holder.infoTextView.text = recepti.info

        holder.bind(recepti)
    }

    override fun getItemCount(): Int {
        return destinationModels.size
    }

    class MyViewHolder(
        itemView: View,
        private val recyclerInterface: kulinarirecviewint
    ) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.textView4)
        val infoTextView: TextView = itemView.findViewById(R.id.textView5)
        fun bind(recepti: recepti) {
            itemView.setOnClickListener {
                recyclerInterface.onClick(recepti)
            }
        }
    }

}
