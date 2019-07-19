package by.itacademy.pvt.skurkoandroidpvt.dz9

import android.graphics.Bitmap
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.FragmentActivity
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

class Dz9Activity : FragmentActivity(), OnMapReadyCallback, Dz9Fragment.Listener {

    private lateinit var googleMap: GoogleMap
    private var isMapReady = false

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<FrameLayout>

    private val carRepository: CarRepository = provideCarRepository()
    private var carList: List<Poi> = emptyList()

    private lateinit var carPoint: Bitmap

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dz9)

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.dz9Container1, Dz9Fragment())
            transaction.commit()
        }

        carPoint = AppCompatResources.getDrawable(this, R.drawable.ic_navigation_black_24dp)!!.toBitmap()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.dz9Container1))

        val params = CoordinateParams(
            Coordinate(0.0, 0.0),
            Coordinate(0.0, 0.0)
        )
        carRepository.getCarByCoordinate(params, { list: List<Poi> ->
            carList = list
            if (isMapReady) {
                setCarsOnMap()
            }
        }, {
            Toast.makeText(this, R.string.dz9_map_error, Toast.LENGTH_SHORT).show()
        })
    }

    private fun setCarsOnMap() {
        val builder = LatLngBounds.builder()

        carList.forEach {
            val coordinates = LatLng(it.coordinate!!.latitude, it.coordinate.longitude)
            builder.include(coordinates)
            googleMap.addMarker(
                MarkerOptions()
                .position(coordinates)
                .icon(BitmapDescriptorFactory.fromBitmap(carPoint))
                .rotation(it.heading!!.toFloat()))
        }
        val bounds = builder.build()
        val boundsPadding = 200
        googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, boundsPadding))
    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap
        isMapReady = true

        if (carList.isNotEmpty()) {
            setCarsOnMap()
        }
    }

    override fun onCarClick(id: String) {
        val carClicked = carList.find { it.id == id }
        carClicked?.let { zoomOnCar(it) }
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    private fun zoomOnCar(it: Poi) {
        val coordinate = LatLng(it.coordinate!!.latitude, it.coordinate.longitude)
        val zoom = 20f
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinate, zoom))
    }
}
