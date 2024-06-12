package com.example.pleszew.wywoz_smieci.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pleszew.ui.theme.CiemnyNiebieski
import com.example.pleszew.ui.theme.JasnyNiebieski
import com.example.pleszew.wywoz_smieci.data.main.GarbageCollection
import com.example.pleszew.wywoz_smieci.presentation.viewmodel.WywozSmieciViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
@Composable
fun DropDownMenus(
    routes: List<GarbageCollection>,
    wywozSmieciViewModel: WywozSmieciViewModel
) {
    var expandedMonth by remember { mutableStateOf(false) }
    var expandedName by remember { mutableStateOf(false) }
    var expandedRoute by remember { mutableStateOf(false) }

    val selectedName by wywozSmieciViewModel.selectedName.collectAsState("")
    val selectedRoute by wywozSmieciViewModel.selectedRoute.collectAsState("")
    val selectedMonth by wywozSmieciViewModel.selectedMonth.collectAsState("")

    val uniqueNames = routes.map { it.name }.distinct()
    val filteredRoutes = routes.filter { it.name == selectedName }
    val uniqueRouteNames = filteredRoutes.map { it.routeName }.distinct().sorted()

    Column(
        modifier = Modifier
    ) {
        // Name Dropdown Menu
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            androidx.compose.material3.ExposedDropdownMenuBox(
                expanded = expandedName,
                onExpandedChange = { expandedName = !expandedName }
            ) {

                TextField(
                    value = selectedName,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedName)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )

                ExposedDropdownMenu(
                    expanded = expandedName,
                    onDismissRequest = { expandedName = false }
                ) {
                    uniqueNames.forEach { item ->
                        androidx.compose.material3.DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                wywozSmieciViewModel.updateSelectedName(item)
                                expandedName = false
                            }
                        )
                    }
                }
            }
        }

        // Route Name Dropdown Menu
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            androidx.compose.material3.ExposedDropdownMenuBox(
                expanded = expandedRoute,
                onExpandedChange = { expandedRoute = !expandedRoute }
            ) {
                TextField(
                    value = selectedRoute,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRoute)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )

                ExposedDropdownMenu(
                    expanded = expandedRoute,
                    onDismissRequest = { expandedRoute = false }
                ) {
                    uniqueRouteNames.forEach { item ->
                        androidx.compose.material3.DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                wywozSmieciViewModel.updateSelectedRoute(item)
                                expandedRoute = false
                            }
                        )
                    }
                }
            }
        }

        // Month Dropdown Menu
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            androidx.compose.material3.ExposedDropdownMenuBox(
                expanded = expandedMonth,
                onExpandedChange = { expandedMonth = !expandedMonth }
            ) {
                TextField(
                    value = selectedMonth,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMonth)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                        .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    )
                )

                ExposedDropdownMenu(
                    expanded = expandedMonth,
                    onDismissRequest = { expandedMonth = false }
                ) {
                    routes.firstOrNull()?.month?.forEach { item ->
                        androidx.compose.material3.DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                wywozSmieciViewModel.updateSelectedMonth(item)
                                expandedMonth = false
                            }
                        )
                    }
                }
            }
        }
    }
}