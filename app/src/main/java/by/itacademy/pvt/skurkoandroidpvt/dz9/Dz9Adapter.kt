package by.itacademy.pvt.skurkoandroidpvt.dz9

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi

class Dz9Adapter(
    private var items: List<Poi>,
    private val listener: ClickListener
) : RecyclerView.Adapter<Dz9ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz9ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dz9, parent, false)
        val holder = Dz9ViewHolder(view)

        holder.itemView.setOnClickListener {
            listener.onCarClicked(items[holder.adapterPosition])
        }
        return holder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Dz9ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(items: List<Poi>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onCarClicked(item: Poi)
    }
}