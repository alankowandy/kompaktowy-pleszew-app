package com.example.pleszew.miasto_samorzad.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pleszew.core.data.MenuItems
import com.example.pleszew.core.domain.SharedViewModel
import com.example.pleszew.core.presentation.PullToRefreshLazyColumn
import com.example.pleszew.miasto_samorzad.data.Office
import com.example.pleszew.miasto_samorzad.presentation.viewmodel.MiastoSamorzadViewModel
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.CiemnyNiebieski
import com.example.pleszew.ui.theme.JasnyNiebieski
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.CameraPositionState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MiastoSamorzadSceen(
    sharedViewModel: SharedViewModel,
    miastoSamorzadViewModel: MiastoSamorzadViewModel = hiltViewModel()
) {
    sharedViewModel.setCurrentScreen(MenuItems.MiastoSamorzad)
    val isLoading by miastoSamorzadViewModel.isLoading.collectAsState(initial = false)
    val offices = miastoSamorzadViewModel.offices.collectAsState(initial = listOf()).value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PullToRefreshLazyColumn(
            listItems = offices!!,
            content = {
                OfficeItems(office = it)
            },
            isRefreshing = isLoading,
            onRefresh = {
                miastoSamorzadViewModel.getOffices()
            }
        )
    }
}

@Composable
fun OfficeItems(
    office: Office
) {
    var showMap by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = JasnyNiebieski,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Bialy,
                elevation = 4.dp
            ) {
                Text(
                    text = office.officeName,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    minLines = 2
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {
                Text(
                    text = buildString {
                        append("ul. ${office.officeStreet}, ${office.officePostal} ${office.officeTown}")
                    },
                    style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                    fontSize = 17.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {
                Text(
                    text = office.officePhoneNumber,
                    style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                    fontSize = 17.sp
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {
                office.officeEmail?.let {
                    Text(
                        text = it,
                        style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                        fontSize = 17.sp
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {
                Button(
                    onClick = {
                        showMap = !showMap
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CiemnyNiebieski
                    )
                ) {
                    Text(
                        text = if (showMap) "UKRYJ MAPĘ" else "POKAŻ NA MAPIE",
                        color = Bialy,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            if (showMap) {
                val cameraPositionState = rememberCameraPositionState {
                    position = CameraPosition(
                        LatLng(office.officeLatitude, office.officeLongitude),
                        15f,
                        0f,
                        0f
                    )
                }
                ShowMap(
                    cameraPositionState = cameraPositionState,
                    office = office
                )
            }
        }
    }
}