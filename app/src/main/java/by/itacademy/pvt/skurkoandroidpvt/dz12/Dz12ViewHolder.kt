package by.itacademy.pvt.skurkoandroidpvt.dz12

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.utils.loadCircularImage

class Dz12ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val imageView = itemView.findViewById<ImageView>(R.id.studentAvatar)
    private val textView = itemView.findViewById<TextView>(R.id.studentName)

    fun bind(item: Student) {
        loadCircularImage(item.imageUrl, imageView)
        textView.text = item.name
    }
}