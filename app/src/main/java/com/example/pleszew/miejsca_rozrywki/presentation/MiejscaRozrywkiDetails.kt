package com.example.pleszew.miejsca_rozrywki.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pleszew.R
import com.example.pleszew.miejsca_rozrywki.data.details.LocationDetails
import com.example.pleszew.miejsca_rozrywki.domain.DetailsViewModel
import com.example.pleszew.ui.theme.JasnyNiebieski

@Composable
fun MiejscaRozrywkiDetailsScreen(
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    navController: NavController,
    locationId: String?
) {
    val samplePlaces = listOf(
        EntertainmentPlaceDetails(
            locationId,
            "Kino Hel & Cafe",
            R.drawable.ic_stopwatch,
            listOf("Pon-Pt 10:00 - 18:00", "Sob 12:00 - 23:00", "Nd 12:00 - 20:00"),
            "+48 (62) 666 666 666",
            "kinohel@kinohel.pl",
            listOf("kinohel.pl", "kino-hel.pl")
        )
    )

    if (locationId != null) {
        detailsViewModel.getDeatils(locationId)
    }

    val details by detailsViewModel.details.collectAsState(initial = listOf())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (!details.isNullOrEmpty()) {
            items(details!!) { detail ->
                EntertainmentPlaceDetailsItem(place = detail)
            }
        }

    }
}

@Composable
fun OpeningHoursBox(openingHours: List<String>) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = "Godziny otwarcia",
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp
                ),
                color = Color.Black
            )
            openingHours.forEach { hour ->
                Text(
                    text = hour,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
            }
        }

    }
}


@Composable
fun EntertainmentPlaceDetailsItem(
    place: LocationDetails
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = JasnyNiebieski,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_stopwatch),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = place.locationName,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.weight(1f),
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                //OpeningHoursBox(place.openHours)
                place.locationPhone?.let { InformationBox(R.drawable.ic_tower, it) }
                place.locationEmail?.let { InformationBox(R.drawable.ic_bus, it) }
//                place.websites?.forEach { website ->
//                    InformationBox(R.drawable.ic_ticket_96, website)
//                }
            }
        }
    }
}

@Composable
fun InformationBox(icon: Int, value: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
            }
        }
    }
}



data class EntertainmentPlaceDetails(
    val id: String?,
    val name: String,
    val icon: Int,
    val openHours: List<String>,
    val phoneNumber: String,
    val email: String,
    val websites: List<String>
)