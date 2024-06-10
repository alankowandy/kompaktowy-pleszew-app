package com.example.pleszew.miasto_samorzad.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pleszew.core.data.MenuItems
import com.example.pleszew.core.domain.SharedViewModel
import com.example.pleszew.core.presentation.PullToRefreshLazyColumn
import com.example.pleszew.miasto_samorzad.data.Office
import com.example.pleszew.miasto_samorzad.presentation.viewmodel.MiastoSamorzadViewModel
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.JasnyNiebieski

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
    Card(
        modifier = Modifier
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = JasnyNiebieski,
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
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
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = buildString {
                        append("ul. ${office.officeStreet}, ${office.officePostal} ${office.officeTown}")
                    }
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = office.officePhoneNumber
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                office.officeEmail?.let {
                    Text(
                        text = it
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /*TODO*/ }
                ) {
                    Text(
                        text = "POKAŻ NA MAPIE"
                    )
                }
            }
        }
    }
}