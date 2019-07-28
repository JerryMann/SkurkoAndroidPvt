package by.itacademy.pvt.skurkoandroidpvt.dz11.dzMVVM

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import by.itacademy.pvt.skurkoandroidpvt.R
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Coordinate
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.CoordinateParams
import by.itacademy.pvt.skurkoandroidpvt.dz9.entity.Poi
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetBehavior

class Dz11Activity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var googleMap: GoogleMap
    private var isMapReady = false

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    private lateinit var carPoint: Bitmap

    private lateinit var viewModel: Dz11ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz9Container1, Dz11Fragment())
            transaction.commit()
        }

        viewModel = ViewModelProviders.of(this).get(Dz11ViewModel::class.java)

        carPoint = AppCompatResources.getDrawable(this, R.drawable.ic_navigation_black_24dp)!!.toBitmap()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9Container1))

        viewModel.state.observe(this, Observer {
            if (it == Dz11State.Loaded) {
                loadLastSelected()
            }
        })

        viewModel.loadCarsList(
            CoordinateParams(
                Coordinate(0.0, 0.0),
                Coordinate(0.0, 0.0)
            )
        )
    }

    private fun loadLastSelected() {
        if (viewModel.hasCars() && isMapReady) {
            setCarsOnMap(viewModel.getCars())

            viewModel.lastSelectedCar.observe(this, Observer { selectedPoi ->
                val carPosition = LatLng(selectedPoi.coordinate!!.latitude, selectedPoi.coordinate.longitude)
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(carPosition, 20f))
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            })
        }
    }

    private fun setCarsOnMap(carList: List<Poi>) {
        val builder = LatLngBounds.builder()

        carList.forEach {
            val coordinates = LatLng(it.coordinate!!.latitude, it.coordinate.longitude)
            builder.include(coordinates)
            googleMap.addMarker(
                MarkerOptions()
                    .position(coordinates)
                    .icon(BitmapDescriptorFactory.fromBitmap(carPoint))
                    .rotation(it.heading!!.toFloat())
            )
        }
        val bounds = builder.build()
        val boundsPadding = 200
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, boundsPadding))
    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap
        isMapReady = true
        loadLastSelected()
    }

    override fun onDestroy() {
        isMapReady = false
        super.onDestroy()
    }
}