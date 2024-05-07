package com.example.pleszew.ui.theme

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
import com.example.pleszew.HomeViewItems

@Composable
fun MenuScreen(homeViewItems: List<HomeViewItems>, modifier: Modifier){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()){
        items(homeViewItems){
            homeViewItem ->
            MenuItem(homeViewItem = homeViewItem)
        }
    }
}

@Composable
fun MenuItem(homeViewItem: HomeViewItems){
    Column(
        modifier = Modifier
            .padding(top = 24.dp, bottom = 24.dp)
            .fillMaxSize()
            .clickable {
                       //TODO
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box (
            modifier = Modifier
                .clip(CircleShape)
                .size(160.dp)
                .background(
                    color = Color(0xFF0068B4)
                ),
            contentAlignment = Alignment.Center
        ){
            Icon(
                painter = painterResource(id = homeViewItem.icon),
                contentDescription = "icon",
                tint = Color(0xFFFFFFFF),
                modifier = Modifier.size(110.dp)
            )
        }

        Text(
            text = homeViewItem.name,
            color = Color.Black,
            style = Typography.bodyLarge,
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}