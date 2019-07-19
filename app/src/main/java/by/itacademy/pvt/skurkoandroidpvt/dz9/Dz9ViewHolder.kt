package by.itacademy.pvt.skurkoandroidpvt.dz9

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi

class Dz9ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val fleetType = itemView.findViewById<TextView>(R.id.fleet_type_id)
    private val latitude = itemView.findViewById<TextView>(R.id.latitude_id)
    private val longitude = itemView.findViewById<TextView>(R.id.longitude_id)

    fun bind(item: Poi) {
        fleetType.text = item.fleetType.toString()
        latitude.text = item.coordinate?.latitude.toString()
        longitude.text = item.coordinate?.longitude.toString()
    }
}