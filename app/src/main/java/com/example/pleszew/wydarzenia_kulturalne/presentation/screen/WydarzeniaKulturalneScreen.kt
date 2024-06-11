package com.example.pleszew.wydarzenia_kulturalne.presentation.screen

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.pleszew.core.domain.WydarzeniaKulturalneDetails
import com.example.pleszew.core.presentation.PullToRefreshLazyColumn
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.JasnyNiebieski
import com.example.pleszew.wydarzenia_kulturalne.data.main.Event
import com.example.pleszew.wydarzenia_kulturalne.presentation.viewmodel.WydarzeniaKulturalneViewModel

@Composable
fun WydarzeniaKulturalneScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    wydarzeniaKulturalneViewModel: WydarzeniaKulturalneViewModel = hiltViewModel()
) {
    sharedViewModel.setCurrentScreen(MenuItems.WydarzeniaKulturalne)
    val isLoading by wydarzeniaKulturalneViewModel.isLoading.collectAsState(initial = false)
    val events = wydarzeniaKulturalneViewModel.events.collectAsState(initial = listOf()).value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        PullToRefreshLazyColumn(
            listItems = events!!,
            content = {
                EventItems(
                    events = it,
                    navController = navController
                )
            },
            isRefreshing = isLoading,
            onRefresh = {
                wydarzeniaKulturalneViewModel.getEvents()
            }
        )
    }
}

@Composable
fun EventItems(
    events: Event,
    navController: NavController
) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                navController.navigate(
                    WydarzeniaKulturalneDetails.createRouteWithParam(
                        events.eventId
                    )
                )
            },
        shape = RoundedCornerShape(8.dp),
        backgroundColor = JasnyNiebieski,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Absolute.Center
            ) {
                Text(
                    text = events.eventDate,
                    style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                    fontSize = 17.sp
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(width = 100.dp, height = 80.dp)
                    .padding(top = 8.dp, start = 12.dp, end = 12.dp, bottom = 8.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = Bialy,
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = events.eventName,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        //minLines = 2
                    )
                }
            }
            if (events.eventEnd.isNotBlank()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.Center
                ) {
                    Text(
                        text = buildString {
                            append("${events.eventStart} - ${events.eventEnd}")
                        },
                        style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                        fontSize = 15.sp
                    )
                }
            } else {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Absolute.Center
                ) {
                    Text(
                        text = events.eventStart,
                        style = androidx.compose.material.MaterialTheme.typography.subtitle1,
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}