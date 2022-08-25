package com.example.maps

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.maps.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val niceBuilding = LatLng(10.8018023,106.7139158)
        listMarker.forEach { marker ->
            val latlng = LatLng(marker.posLat , marker.posLng)
            val iconMarker = MarkerOptions().position(latlng).title(marker.title)
                .icon( BitmapDescriptorFactory.fromBitmap(generateSmallIcon(marker.icoMarker)))
            mMap.addMarker(iconMarker)?.tag = marker
        }
        mMap.apply {
            setMaxZoomPreference(20.0f)
            animateCamera(CameraUpdateFactory.newLatLngZoom(niceBuilding,16F ))
            uiSettings.isZoomControlsEnabled = true
            uiSettings.isMyLocationButtonEnabled = true
            mapType = GoogleMap.MAP_TYPE_NORMAL
        }
        onClickMarker()
    }

    private fun onClickMarker() {
        mMap.setOnMarkerClickListener {
            val data = it.tag as Marker
            val dialog = MarkerDetailBottomSheetFragment()
            val bundle = Bundle()
            bundle.putSerializable("Marker", data)
            dialog.arguments = bundle
            dialog.show(supportFragmentManager ,"Marker" )
            true
        }
    }

    private fun generateSmallIcon( drawable : Int): Bitmap {
        val height = 150
        val width = 150
        val bitmap = BitmapFactory.decodeResource(this.resources, drawable)
        return Bitmap.createScaledBitmap(bitmap, width, height, false)
    }

    companion object {
        private val marker_one = Marker(
            id = 0,
            address = "22 Nguyễn Gia Trí, Phường 25, Bình Thạnh, Thành phố Hồ Chí Minh, Việt Nam",
            imgAddress = "",
            title = "Three O’clock",
            posLat = 10.8018023,
            posLng = 106.7117218,
            icoMarker = R.drawable.img_one
        )
        private val marker_two = Marker(
            id = 1,
            address = "6 Nguyễn Gia Trí, Phường 25, Bình Thạnh, Thành phố Hồ Chí Minh, Việt Nam",
            imgAddress = "",
            title = "Katinat Nguyễn Gia Trí",
            posLat = 10.8031514,
            posLng = 106.7152124,
            icoMarker = R.drawable.img_two
        )
        private val marker_three = Marker(
            id = 2,
            address = "21 Nguyễn Gia Trí, Phường 25, Bình Thạnh, Thành phố Hồ Chí Minh, Việt Nam",
            imgAddress = "",
            title = "Cafe Tít Tít",
            posLat = 10.8021978,
            posLng = 106.7156633,
            icoMarker = R.drawable.img_three
        )
        val listMarker = mutableListOf(
            marker_one, marker_two, marker_three
        )
    }
}
