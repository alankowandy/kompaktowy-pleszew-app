package com.example.pleszew.city15.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pleszew.ui.theme.CiemnyNiebieski

@Composable
fun Miasto15() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = "https://miasto15.pl/wp-content/uploads/2021/07/motyw.jpg"),
            contentDescription = "Miasto15"
        )
        Text(
            text = "Zmień perspektywę!\n" +
                    "Wszędzie dotrzesz w kwadrans. Kompaktowe miasto, to miasto 15 minut. Pleszew - pierwsze w Polsce miasto15."
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = CiemnyNiebieski
            )
        ) {
            Text(
                text = "CZYTAJ DALEJ"
            )
        }

    }
}