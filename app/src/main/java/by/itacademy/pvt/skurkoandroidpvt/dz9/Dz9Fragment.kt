package by.itacademy.pvt.skurkoandroidpvt.dz9

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Coordinate
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi

class Dz9Fragment : Fragment(), Dz9Adapter.ClickListener {

    private var clickListener: Listener? = null
    private val carRepository: CarRepository = provideCarRepository()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz9, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val carListAdapter = Dz9Adapter(emptyList(), this)

        val recyclerView = view.findViewById<RecyclerView>(R.id.dz9Recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val params = CoordinateParams(
            Coordinate(0.0, 0.0),
            Coordinate(0.0, 0.0)
        )

        recyclerView.adapter = carListAdapter

        carRepository.getCarByCoordinate(params, { list: List<Poi> ->
            carListAdapter.updateItems(list)
        }, { throwable: Throwable ->
            Toast.makeText(context, R.string.dz9_map_error, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            clickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        clickListener = null
    }

    override fun onCarClicked(item: Poi) {
        clickListener?.onCarClick(item.id)
    }

    interface Listener {
        fun onCarClick(id: String)
    }
}