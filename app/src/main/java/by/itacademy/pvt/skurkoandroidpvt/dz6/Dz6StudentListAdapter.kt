package by.itacademy.pvt.skurkoandroidpvt.dz6

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R

class Dz6StudentListAdapter(
    private var items: List<Student>,
    private val listener: ClickListener
) : RecyclerView.Adapter<Dz6StudentListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Dz6StudentListViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student_dz6, parent, false)
        val holder = Dz6StudentListViewHolder(view)

        holder.itemView.setOnClickListener {
            listener.onStudentClick(items[holder.adapterPosition])
        }
        return holder
    }

    override fun onBindViewHolder(holder: Dz6StudentListViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateList(items: List<Student>) {
        this.items = items
        notifyDataSetChanged()
    }

    interface ClickListener {
        fun onStudentClick(item: Student)
    }
}