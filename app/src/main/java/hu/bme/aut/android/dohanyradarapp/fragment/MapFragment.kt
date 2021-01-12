package hu.bme.aut.android.dohanyradarapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import hu.bme.aut.android.dohanyradarapp.DetailHelper
import hu.bme.aut.android.dohanyradarapp.R
import hu.bme.aut.android.dohanyradarapp.model.SharedModel
import hu.bme.aut.android.dohanyradarapp.model.Store


class MapFragment: Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private val model: SharedModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_maps,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.setOnMarkerClickListener(this)
        showStoresOnMap()
        model.stores?.observe(this, Observer<List<Store>> {
           showStoresOnMap()
        })
    }

    private fun showStoresOnMap() {
        if (!model.stores.value.isNullOrEmpty() && mMap != null) {
            var aStorePos = LatLng(-34.0, 151.0)
            for (store in model.stores.value!!.toList()) {
                aStorePos = LatLng(store.latitude.toDouble(), store.longitude.toDouble())
                mMap.addMarker(
                    MarkerOptions()
                        .position(aStorePos)
                        .title(store.name)
                        .icon(BitmapDescriptorFactory.defaultMarker(if (store.isOpen) BitmapDescriptorFactory.HUE_GREEN else BitmapDescriptorFactory.HUE_RED))

                )
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLng(aStorePos))
        }
    }

    override fun onMarkerClick(marker: Marker?): Boolean {
        var storeId = -1
        for (store in model.stores.value?.toList()!!) {
            if (store.name.equals(marker!!.title))
                storeId = store.id.toInt()
        }
        if(storeId >0)
            DetailHelper.popUpDetails(storeId,this)

        return false
    }
}