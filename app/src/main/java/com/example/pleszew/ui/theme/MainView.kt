package com.example.pleszew.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pleszew.MainViewModel
import com.example.pleszew.Screen
import com.example.pleszew.screensInDrawer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(){

    val scaffoldState: ScaffoldState = rememberScaffoldState()
    val scope: CoroutineScope = rememberCoroutineScope()
    val viewModel: MainViewModel = viewModel()

    // Na jakim ekranie się obecnie znajdujemy
    val controller: NavController = rememberNavController()
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val currentScreen = remember{
        viewModel.currentScreen.value
    }

    val title = remember{
        mutableStateOf(currentScreen.title)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Kompaktowy Pleszew") },
                navigationIcon = { IconButton(onClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                }
            })
        },
        scaffoldState = scaffoldState,
        drawerContent = {
            LazyColumn(Modifier.padding(16.dp)){
                items(screensInDrawer){
                    item ->
                    DrawerItem(selected = currentRoute == item.dRoute, item = item) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                        controller.navigate(item.dRoute)
                        title.value = item.dTtitle
                    }
                }
            }
        }
    ) {
        Text(text = "Text", modifier = Modifier.padding(it))
        //MenuScreen(homeViewItems = )
    }

}

@Composable
fun DrawerItem(
    selected: Boolean,
    item: Screen.DrawerScreen,
    onDrawerItemClicked: () -> Unit
){
    val dSelected: Int = if (selected) 250 else 100
    Row (
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .clickable { onDrawerItemClicked() }
    ){
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.dTtitle,
            Modifier
                .padding(end = 8.dp)
                .size(36.dp)
        )
        Text(
            text = item.dTtitle,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(top = 9.dp)
            //fontWeight = FontWeight(dSelected)
        )
    }
}

@Composable
fun Navigation(navController: NavController, viewModel: MainViewModel, pd: PaddingValues){

}










