package by.itacademy.pvt.skurkoandroidpvt.dz6

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.utils.loadCircularImage

class Dz6StudentListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val imageStudent = itemView.findViewById<ImageView>(R.id.student_avatar)
    private val nameStudent = itemView.findViewById<TextView>(R.id.student_name)

    fun bind(item: Student) {
        loadCircularImage(item.imageUrl, imageStudent)
        nameStudent.text = item.name
    }
}