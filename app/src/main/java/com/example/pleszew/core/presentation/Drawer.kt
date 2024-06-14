package com.example.pleszew.core.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.pleszew.core.data.MenuItems

@Composable
fun DrawerItem(
    selected: Boolean,
    item: MenuItems,
    onDrawerItemClicked: () -> Unit
){
    val dSelected: FontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    Row (
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .clickable { onDrawerItemClicked() }
    ){
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = item.title,
            tint = Color.Black,
            modifier = Modifier
                .padding(end = 8.dp)
                .size(36.dp)
        )
        Text(
            text = item.title,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black,
            modifier = Modifier.padding(top = 9.dp),
            fontWeight = dSelected
        )
    }
}