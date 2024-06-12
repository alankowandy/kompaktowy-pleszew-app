package com.example.pleszew.wywoz_smieci.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.pleszew.core.data.MenuItems
import com.example.pleszew.core.domain.SharedViewModel
import com.example.pleszew.core.domain.WywozSmieciDetails
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.CiemnyNiebieski
import com.example.pleszew.ui.theme.JasnyNiebieski
import com.example.pleszew.wywoz_smieci.data.main.GarbageCollection
import com.example.pleszew.wywoz_smieci.presentation.viewmodel.WywozSmieciViewModel
import com.example.pleszew.wywoz_smieci.presentation.widgets.DropDownMenus

@Composable
fun WywozSmieciScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel,
    wywozSmieciViewModel: WywozSmieciViewModel = hiltViewModel()
) {
    sharedViewModel.setCurrentScreen(MenuItems.WywozSmieci)
    val isEnabled by wywozSmieciViewModel.isEnabled.collectAsState(initial = false)
    val routes = wywozSmieciViewModel.routes.collectAsState(initial = listOf()).value
    val selectedMonth by wywozSmieciViewModel.selectedMonth.collectAsState(initial = "")
    val selectedRouteId by wywozSmieciViewModel.selectedRouteId.collectAsState(initial = "")
    val routeDetail by wywozSmieciViewModel.selectedRouteDetails.collectAsState(initial = "")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = JasnyNiebieski, shape = RoundedCornerShape(16.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Harmonogram wywozu śmieci",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    // Użycie nowego, bardziej interaktywnego pola wyboru miasta
//                    OutlinedTextField(
//                        value = "",
//                        onValueChange = {},
//                        label = { Text(text = "Miasto", color = Color.Black) },
//                        textStyle = LocalTextStyle.current.copy(color = Color.Black),
//                        singleLine = true,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(color = Color.Transparent)
//                            .padding(bottom = 8.dp)
//                    )
//
//                    // Użycie nowego, bardziej interaktywnego pola wyboru trasy
//                    OutlinedTextField(
//                        value = "",
//                        onValueChange = {},
//                        label = { Text("Trasa", color = Color.Black) },
//                        textStyle = LocalTextStyle.current.copy(color = Color.Black),
//                        singleLine = true,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(color = Color.Transparent)
//                            .padding(bottom = 8.dp)
//                    )
//
//                    // Użycie nowego, bardziej interaktywnego pola wyboru miesiąca
//                    OutlinedTextField(
//                        value = "",
//                        onValueChange = {},
//                        label = { Text("Miesiąc", color = Color.Black) },
//                        textStyle = LocalTextStyle.current.copy(color = Color.Black),
//                        singleLine = true,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .background(color = Color.Transparent)
//                            .padding(bottom = 8.dp)
//                    )

                    if (!routes.isNullOrEmpty()) {
                        DropDownMenus(
                            routes = routes,
                            wywozSmieciViewModel = wywozSmieciViewModel
                        )
                    }

                    Button(
                        onClick = {
                            navController.navigate(
                                WywozSmieciDetails.createRouteWithParam(
                                    routeId = selectedRouteId,
                                    month = selectedMonth
                                )
                            )
                        },
                        enabled = isEnabled,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(backgroundColor = CiemnyNiebieski)
                    ) {
                        Text("Szukaj".uppercase(), color = Color.White)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = CiemnyNiebieski, shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                Text(
                    text = routeDetail,
                    //text = "Obszar, którego dotyczy harmonogram: Korzkwy 30a; Kowalew: ul. Jarocińska 1-7 oraz Pleszew ulice: Bogusza 1, Daszyńskiego 1-11, Kaliska 3, Kowalska 1,3, Koźmińska 1-12, Kraszewskiego 1-10, Lipowa 1,12, Plac Kościelny 1-6, Plac Kościuszki 4,4a,5, Poznańska 2-147, Rynek 1-24, Szpitalna 6-21, Tyniec 3, 4, 5, Wąska 2,3,7, Wierzbowa - Ogrody, Zamkowa 1-5.",
                    color = Color.White,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}
