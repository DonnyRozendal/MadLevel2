package nl.hva.madlevel2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_image_row.view.*

class MainAdapter(val itemList: MutableList<City>, private val listener: (City) -> Unit) :
        RecyclerView.Adapter<MainAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        return CustomViewHolder(LayoutInflater
                .from(parent.context)
                .inflate(R.layout.view_image_row, parent, false))
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(itemList[position], listener)
    }

    fun deleteItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class CustomViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: City, listener: (City) -> Unit) = with(view) {
            imageView.setImageResource(item.id)
            setOnClickListener { listener(item) }
        }

    }

}