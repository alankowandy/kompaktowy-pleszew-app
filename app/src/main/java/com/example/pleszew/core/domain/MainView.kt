package com.example.pleszew.core.domain

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
import com.example.pleszew.core.data.Screen
import com.example.pleszew.core.data.screensInDrawer
import com.example.pleszew.core.presentation.DrawerItem
import com.example.pleszew.main.presentation.MenuScreen
import com.example.pleszew.miejsca_rozrywki.presentation.MiejscaRozrywkiScreen
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.CiemnyNiebieski
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(){

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
                            navController.navigate(item.route)
                        }
                    )
                }
            }
        }
    ) {innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.HomePage.route,
            Modifier.padding(innerPadding)
        ) {
            // Nawigacja na stronę główną
            composable(Screen.HomePage.route) {
                MenuScreen(
                    navController = navController,
                    sharedViewModel = sharedViewModel
                )
            }

            navigation(
                startDestination = Screen.WywozSmieciStart.route,
                route = Screen.WywozSmieci.route
            ) {
                composable(Screen.WywozSmieciStart.route) {

                }

                composable(Screen.WywozSmieciDetails.route) {

                }
            }

            navigation(
                startDestination = Screen.KomunikacjaMiejskaStart.route,
                route = Screen.KomunikacjaMiejska.route
            ) {
                composable(Screen.KomunikacjaMiejskaStart.route) {

                }

                composable(Screen.KomunikacjaMiejskaDetails.route) {

                }
            }

            navigation(
                startDestination = Screen.WydarzeniaKulturalneStart.route,
                route = Screen.WydarzeniaKulturalne.route
            ) {
                composable(Screen.WydarzeniaKulturalneStart.route) {

                }

                composable(Screen.WydarzeniaKulturalneDetails.route) {

                }
            }

            navigation(
                startDestination = Screen.MiejscaRozrywkiStart.route,
                route = Screen.MiejscaRozrywki.route
            ) {
                composable(Screen.MiejscaRozrywkiStart.route) {
                    MiejscaRozrywkiScreen(
                        navController = navController,
                        sharedViewModel = sharedViewModel
                    )
                }

                composable(Screen.MiejscaRozrywkiDetails.route) {

                }
            }

            composable(Screen.MiastoSamorzad.route) {

            }

            composable(Screen.Miasto15.route) {
                Miasto15(
                    sharedViewModel = sharedViewModel
                )
            }
        }
    }
}










