package com.example.pleszew.miasto_samorzad.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import com.example.pleszew.miasto_samorzad.data.Office
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState

@Composable
fun ShowMap(
    cameraPositionState: CameraPositionState,
    office: Office,
) {
    val markerState = remember {
        MarkerState(
            position = LatLng(office.officeLatitude, office.officeLongitude)
        )
    }

    Box(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(16.dp)
    ) {
        GoogleMap(
            cameraPositionState = cameraPositionState
        ) {
            Marker(
                state = markerState,
                title = office.officeName,
                snippet = office.officeStreet
            )
        }
    }
}