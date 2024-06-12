package com.example.pleszew.komunikacja_miejska.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pleszew.core.data.MenuItems
import com.example.pleszew.core.domain.SharedViewModel
import com.example.pleszew.komunikacja_miejska.presentation.viewmodel.KomunikacjaMiejskaViewModel
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.CiemnyNiebieski
import com.example.pleszew.ui.theme.JasnyNiebieski

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KomunikacjaMiejskaScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    komunikacjaMiejskaViewModel: KomunikacjaMiejskaViewModel = hiltViewModel()
) {
    sharedViewModel.setCurrentScreen(MenuItems.KomunikacjaMiejska)
    val searchText by komunikacjaMiejskaViewModel.searchText.collectAsState(initial = "")
    val isActive by komunikacjaMiejskaViewModel.isActive.collectAsState(initial = false)
    
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Card(
            backgroundColor = CiemnyNiebieski,
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .height(50.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "ROZKŁAD JAZDY",
                    color = Bialy,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Card(
            backgroundColor = JasnyNiebieski,
            elevation = 4.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                SearchBar(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = SearchBarDefaults.colors(
                        containerColor = Bialy
                    ),
                    query = searchText,
                    onQueryChange = {
                        komunikacjaMiejskaViewModel.updateSearchText(it)
                    },
                    onSearch = {
                        komunikacjaMiejskaViewModel.updateIsActive(false)
                    },
                    active = isActive,
                    onActiveChange = {
                        komunikacjaMiejskaViewModel.updateIsActive(it)
                    },
                    placeholder = { 
                        Text(
                            text = "Wyszukaj przystanek",
                            modifier = Modifier
                                .alpha(0.5f)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    trailingIcon = {
                        if (isActive) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                modifier = Modifier
                                    .clickable {
                                        komunikacjaMiejskaViewModel.updateIsActive(false)
                                    }
                            )
                        }
                    }
                ) {
                    
                }
            }
        }
    }
}