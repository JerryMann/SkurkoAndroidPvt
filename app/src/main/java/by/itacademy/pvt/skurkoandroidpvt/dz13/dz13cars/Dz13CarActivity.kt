package by.itacademy.pvt.skurkoandroidpvt.dz13.dz13cars

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
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
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.activity_dz9.*

class Dz13CarActivity : FragmentActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private var isMapReady = false

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>
    private lateinit var carPoint: Bitmap

    private lateinit var viewModel: Dz13CarViewModel

    companion object {
        const val PADDING = 100
        const val ZOOM = 18f
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        viewModel = ViewModelProviders.of(this).get(Dz13CarViewModel::class.java)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.dz9Container1, Dz13CarFragment())
                .commit()
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottomSheetBehavior = BottomSheetBehavior.from(dz9Container1)

        carPoint = AppCompatResources.getDrawable(this, R.drawable.ic_navigation_black_24dp)!!.toBitmap()

        val params = CoordinateParams(
            Coordinate(0.0, 0.0),
            Coordinate(0.0, 0.0)
        )

        viewModel
            .state
            .observe(this, Observer {
                when (it) {
                    is Dz13CarState.GetAll -> {
                        if (isMapReady) setCarsOnMap(it.poiList)
                    }
                    is Dz13CarState.GetOneCar -> {
                        if (isMapReady) onCarClicked(it.poi)
                    }
                    is Dz13CarState.Error -> {
                        Toast.makeText(this, getText(R.string.dz9_map_error), Toast.LENGTH_SHORT).show()
                    }
                }
            })

        viewModel.loadCarList(params)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        isMapReady = true
        viewModel
            .state
            .observe(this, Observer {
                when (it) {
                    is Dz13CarState.GetAll -> {
                        setCarsOnMap(it.poiList)
                    }
                }
            })
    }

    private fun setCarsOnMap(poiList: List<Poi>) {
        var place: LatLng
        val builder = LatLngBounds.Builder()
        map.clear()
        poiList.forEach {
            place = LatLng(it.coordinate!!.latitude, it.coordinate.longitude)
            map.addMarker(
                MarkerOptions()
                    .position(place)
                    .icon(BitmapDescriptorFactory.fromBitmap(carPoint))
                    .rotation(it.heading!!.toFloat())
            )
            builder.include(place)
        }
        val bounds = builder.build()
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, PADDING))
    }

    private fun onCarClicked(poi: Poi) {
        val poiPlace = LatLng(poi.coordinate!!.latitude, poi.coordinate.longitude)
        map.clear()
        map.addMarker(
            MarkerOptions()
                .position(poiPlace)
                .icon(BitmapDescriptorFactory.fromBitmap(carPoint))
                .rotation(poi.heading!!.toFloat())
        )
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(poiPlace, ZOOM))
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }
}