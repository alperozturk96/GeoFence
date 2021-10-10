package com.coolnexttech.geofence.viewmodel

import android.location.Location
import androidx.lifecycle.ViewModel
import com.coolnexttech.geofence.utils.SingleLiveEvent
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil

class MainVM:ViewModel() {
    val showNotificationEvent by lazy { SingleLiveEvent<Void>() }

    fun checkForGeoFenceEntry(userLocation: Location, geofenceLat: Double, geofenceLong: Double, radius: Double) {
        val startLatLng = LatLng(userLocation.latitude, userLocation.longitude)
        val geofenceLatLng = LatLng(geofenceLat, geofenceLong)

        val distanceInMeters = SphericalUtil.computeDistanceBetween(startLatLng, geofenceLatLng)

        if (distanceInMeters < radius) {
            // User is inside the Geo-fence
            showNotificationEvent.call()
        }
    }
}