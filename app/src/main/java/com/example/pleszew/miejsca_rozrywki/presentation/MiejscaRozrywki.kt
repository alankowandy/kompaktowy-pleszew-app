package com.example.pleszew.miejsca_rozrywki.presentation

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pleszew.R
import com.example.pleszew.core.data.MenuItems
import com.example.pleszew.core.domain.MiejscaRozrywkiDetails
import com.example.pleszew.core.domain.SharedViewModel
import com.example.pleszew.core.presentation.PullToRefreshLazyColumn
import com.example.pleszew.miejsca_rozrywki.data.start.Locations
import com.example.pleszew.miejsca_rozrywki.domain.MiejscaRozrywkiViewModel
import com.example.pleszew.ui.theme.JasnyNiebieski

@Composable
fun MiejscaRozrywkiScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    miejscaRozrywkiViewModel: MiejscaRozrywkiViewModel = hiltViewModel()
) {
    sharedViewModel.setCurrentScreen(MenuItems.MiejscaRozrywki)
    val isLoading by miejscaRozrywkiViewModel.isLoading.collectAsState(initial = false)
    val locations = miejscaRozrywkiViewModel.locations.collectAsState(initial = listOf()).value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PullToRefreshLazyColumn(
            listItems = locations!!,
            content = {
                EntertainmentPlaceItem(
                    place = it,
                    navController = navController
                )
            },
            isRefreshing = isLoading,
            onRefresh = {
                miejscaRozrywkiViewModel.getLocations()
            }
        )
    }
}

@Composable
fun EntertainmentPlaceItem(
    place: Locations,
    navController: NavController
) {

    @DrawableRes val icon: Int = when (place.id) {
        "1" -> { R.drawable.ic_kino_hel }
        "2" -> { R.drawable.ic_recycling }
        "3" -> { R.drawable.ic_muzeum_piekarstwa }
        "4" -> { R.drawable.ic_muzeum }
        "5" -> { R.drawable.ic_park }
        "6" -> { R.drawable.ic_basen }
        "7" -> { R.drawable.ic_bowling }
        "8" -> { R.drawable.ic_book }
        "9" -> { R.drawable.ic_amfiteatr }
        "10" -> { R.drawable.ic_muzeum }
        "11" -> { R.drawable.ic_sport }
        "12" -> { R.drawable.ic_silownia }
        "13" -> { R.drawable.ic_centrum_wspierania }
        "14" -> { R.drawable.ic_stadion_miejski }
        else -> { R.drawable.ic_bus }
    }

    Card(
        modifier = Modifier
            .padding(15.dp)
            .clickable {
                navController.navigate(
                    MiejscaRozrywkiDetails.createRouteWithParam(
                        place.id
                    )
                )
            },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = JasnyNiebieski,
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .background(Color.White, shape = RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center,
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = place.name,
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f)) // Dodanie pustego przestrzeni w celu wyrównania tekstu do prawej
            Text(
                text = place.name.uppercase(),
                style = MaterialTheme.typography.h6.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium),
                modifier = Modifier
                    .fillMaxWidth() // Wypełnienie maksymalnej szerokości
                    .align(Alignment.CenterVertically) // Wyśrodkowanie wertykalne
                    .padding(end = 16.dp), // Dodanie odstępu z prawej strony
                textAlign = TextAlign.End // Wyrównanie tekstu do prawej strony
            )
        }
    }
}