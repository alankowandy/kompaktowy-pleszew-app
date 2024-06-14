package com.example.pleszew.core.domain

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.pleszew.city15.presentation.Miasto15
import com.example.pleszew.core.data.MenuItems
import com.example.pleszew.core.data.screensInDrawer
import com.example.pleszew.core.presentation.DrawerItem
import com.example.pleszew.komunikacja_miejska.presentation.screen.KomunikacjaMiejskaHoursScreen
import com.example.pleszew.komunikacja_miejska.presentation.screen.KomunikacjaMiejskaScreen
import com.example.pleszew.main.presentation.MenuScreen
import com.example.pleszew.miasto_samorzad.presentation.screen.MiastoSamorzadSceen
import com.example.pleszew.miejsca_rozrywki.presentation.MiejscaRozrywkiDetailsScreen
import com.example.pleszew.miejsca_rozrywki.presentation.MiejscaRozrywkiScreen
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.CiemnyNiebieski
import com.example.pleszew.wydarzenia_kulturalne.presentation.screen.WydarzeniaKulturalneDetailsScreen
import com.example.pleszew.wydarzenia_kulturalne.presentation.screen.WydarzeniaKulturalneScreen
import com.example.pleszew.wywoz_smieci.presentation.screen.WywozSmieciDetailsScreen
import com.example.pleszew.wywoz_smieci.presentation.screen.WywozSmieciScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView() {

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val sharedViewModel: SharedViewModel = viewModel()

    // Na jakim ekranie się obecnie znajdujemy
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.parent?.route
        ?: navBackStackEntry?.destination?.route

    // Dynamicznie aktualizowany tytul na pasku na podstawie shared view modelu
    val title by sharedViewModel.title.collectAsState(initial = "")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { title?.let { Text(text = it) } },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = CiemnyNiebieski,
                    navigationIconContentColor = Bialy,
                    titleContentColor = Bialy
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                }
            )
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn(
                modifier = Modifier.padding(16.dp)
            ){
                items(screensInDrawer){
                    item ->
                    DrawerItem(
                        selected = currentRoute == item.route,
                        item = item,
                        onDrawerItemClicked = {
                            scope.launch {
                                scaffoldState.drawerState.close()
                            }
                            navController.navigate(item.route) {
                                popUpTo(MenuItems.HomePage.route)
                            }
                        }
                    )
                }
            }
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = HomePage.route,
            Modifier.padding(innerPadding)
        ) {
            // Nawigacja na stronę główną
            composable(HomePage.route) {
                MenuScreen(
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }

            navigation(
                startDestination = WywozSmieciStart.route,
                route = WywozSmieci.route
            ) {
                composable(WywozSmieciStart.route) {
                    WywozSmieciScreen(
                        navController = navController,
                        sharedViewModel = sharedViewModel
                    )
                }

                composable(
                    route = "${WywozSmieciDetails.route}/{${WywozSmieciDetails.routeId}}/{${WywozSmieciDetails.month}}",
                    arguments = WywozSmieciDetails.arguments
                ) { navBackStackEntry ->
                    val routeId =
                        navBackStackEntry.arguments?.getString(WywozSmieciDetails.routeId)
                    val month =
                        navBackStackEntry.arguments?.getString(WywozSmieciDetails.month)
                    WywozSmieciDetailsScreen(
                        routeId = routeId,
                        month = month
                    )
                }
            }

            navigation(
                startDestination = KomunikacjaMiejskaStart.route,
                route = KomunikacjaMiejska.route
            ) {
                composable(KomunikacjaMiejskaStart.route) {
                    KomunikacjaMiejskaScreen(
                        navController = navController,
                        sharedViewModel = sharedViewModel
                    )
                }

                composable(
                    route = "${KomunikacjaMiejskaDetails.route}/{${KomunikacjaMiejskaDetails.stopName}}/{${KomunikacjaMiejskaDetails.lineId}}",
                    arguments = KomunikacjaMiejskaDetails.arguments
                ) {navBackStackEntry ->
                    val stopName =
                        navBackStackEntry.arguments?.getString(KomunikacjaMiejskaDetails.stopName)
                    val lineId =
                        navBackStackEntry.arguments?.getString(KomunikacjaMiejskaDetails.lineId)
                    KomunikacjaMiejskaHoursScreen(
                        stopName = stopName,
                        lineId = lineId
                    )
                }
            }

            navigation(
                startDestination = WydarzeniaKulturalneStart.route,
                route = WydarzeniaKulturalne.route
            ) {
                composable(WydarzeniaKulturalneStart.route) {
                    WydarzeniaKulturalneScreen(
                        navController = navController,
                        sharedViewModel = sharedViewModel
                    )
                }

                composable(
                    route = "${WydarzeniaKulturalneDetails.route}/{${WydarzeniaKulturalneDetails.eventId}}",
                    arguments = WydarzeniaKulturalneDetails.arguments
                ) {navBackStackEntry ->
                    val eventId =
                        navBackStackEntry.arguments?.getString(WydarzeniaKulturalneDetails.eventId)
                    WydarzeniaKulturalneDetailsScreen(
                        eventId = eventId
                    )
                }
            }

            navigation(
                startDestination = MiejscaRozrywkiStart.route,
                route = MiejscaRozrywki.route
            ) {
                composable(MiejscaRozrywkiStart.route) {
                    MiejscaRozrywkiScreen(
                        navController = navController,
                        sharedViewModel = sharedViewModel
                    )
                }

                composable(
                    route = "${MiejscaRozrywkiDetails.route}/{${MiejscaRozrywkiDetails.locationId}}",
                    arguments = MiejscaRozrywkiDetails.arguments
                ) {navBackStackEntry ->
                    val locationId =
                        navBackStackEntry.arguments?.getString(MiejscaRozrywkiDetails.locationId)
                    MiejscaRozrywkiDetailsScreen(
                        locationId = locationId
                    )
                }
            }

            composable(MiastoSamorzad.route) {
                MiastoSamorzadSceen(
                    sharedViewModel = sharedViewModel
                )
            }

            composable(Miasto15.route) {
                Miasto15(
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}










