package com.example.pleszew.city15.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.pleszew.core.data.MenuItems
import com.example.pleszew.core.domain.SharedViewModel
import com.example.pleszew.ui.theme.CiemnyNiebieski

@Composable
fun Miasto15(
    sharedViewModel: SharedViewModel
) {
    sharedViewModel.setCurrentScreen(MenuItems.Miasto15)

    val context = LocalContext.current

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
                    "Wszędzie dotrzesz w kwadrans. Kompaktowe miasto, to miasto 15 minut. Pleszew - pierwsze w Polsce miasto15.",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 16.dp)
        )
        Button(
            onClick = {
                openWebPage(
                    url = "https://miasto15.pl",
                    context = context
                )
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = CiemnyNiebieski
            ),
            modifier = Modifier
                .padding(top = 16.dp)
        ) {
            Text(
                text = "CZYTAJ DALEJ"
            )
        }

    }
}

fun openWebPage(
    url: String,
    context: Context
) {
    val webpage: Uri = Uri.parse(url)
    val intent = Intent(Intent.ACTION_VIEW, webpage)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
}