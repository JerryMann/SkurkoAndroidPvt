package by.itacademy.pvt.skurkoandroidpvt.dz13.dz13cars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz9.Dz9Adapter
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi

class Dz13CarFragment : Fragment(), Dz9Adapter.ClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: Dz13CarViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_dz9, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = view.findViewById(R.id.dz9Recycler)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!).get(Dz13CarViewModel::class.java)

        viewModel
            .state
            .observe(this, Observer {
                when (it) {
                    is Dz13CarState.GetAll -> {
                        recyclerView.adapter = Dz9Adapter(it.poiList, this)
                    }
                }
            })
    }

    override fun onCarClicked(item: Poi) {
        viewModel.showCar(item)
    }
}