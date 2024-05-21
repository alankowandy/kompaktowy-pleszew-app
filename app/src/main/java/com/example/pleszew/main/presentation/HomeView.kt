package com.example.pleszew.main.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.pleszew.core.data.MenuItems
import com.example.pleszew.core.data.screensInMenu
import com.example.pleszew.core.domain.SharedViewModel
import com.example.pleszew.ui.theme.Typography

@Composable
fun MenuScreen(
    navController: NavController,
    sharedViewModel: SharedViewModel
){
    sharedViewModel.setCurrentScreen(MenuItems.HomePage)
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(screensInMenu){
            item ->
            MenuItem(
                navController = navController,
                item = item
            )
        }
    }
}

@Composable
fun MenuItem(
    navController: NavController,
    item: MenuItems
){
    Column(
        modifier = Modifier
            .padding(top = 24.dp, bottom = 24.dp)
            .fillMaxSize()
            .clickable {
                navController.navigate(item.route)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box (
            modifier = Modifier
                .clip(CircleShape)
                .size(144.dp)
                .background(
                    color = Color(0xFF0068B4)
                ),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = item.icon),
                contentDescription = "icon",
                tint = Color(0xFFFFFFFF),
                modifier = Modifier.size(94.dp)
            )
        }

        Text(
            text = item.title,
            color = Color.Black,
            style = Typography.bodyMedium,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}