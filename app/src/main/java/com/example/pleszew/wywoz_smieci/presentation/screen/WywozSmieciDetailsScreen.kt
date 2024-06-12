package com.example.pleszew.wywoz_smieci.presentation.screen

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.CalendarContract
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.JasnyNiebieski
import com.example.pleszew.ui.theme.Zielony
import com.example.pleszew.wywoz_smieci.presentation.viewmodel.WywozSmieciDetailsViewModel
import com.example.pleszew.R
import com.example.pleszew.ui.theme.Fioletowy
import com.example.pleszew.ui.theme.Zolty
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WywozSmieciDetailsScreen(
    routeId: String?,
    month: String?,
    wywozSmieciDetailsViewModel: WywozSmieciDetailsViewModel = hiltViewModel()
) {
    val collectionDetails = wywozSmieciDetailsViewModel.collectionDetails.collectAsState(initial = listOf()).value
    val detailName by wywozSmieciDetailsViewModel.detailName.collectAsState(initial = "Miasto")
    val routeName by wywozSmieciDetailsViewModel.routeName.collectAsState(initial = "Trasa")
    val collectedRoute by wywozSmieciDetailsViewModel.collectedRoute.collectAsState(initial = "")
    val uniqueGarbageTypes by wywozSmieciDetailsViewModel.uniqueGarbageTypes.collectAsState(initial = listOf())
    val context = LocalContext.current
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            elevation = 4.dp,
            backgroundColor = JasnyNiebieski,
            shape = RoundedCornerShape(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = 4.dp,
                    backgroundColor = Bialy
                ) {
                    Text(
                        text = detailName,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    )
                }
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    elevation = 4.dp,
                    backgroundColor = Bialy
                ) {
                    Text(
                        text = routeName,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.h6.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium)
                    )
                }
            }
        }
        Text(
            text = collectedRoute,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
        )
        uniqueGarbageTypes.forEach { type ->
            GarbageTypeBox(
                garbageType = type,
                wywozSmieciDetailsViewModel = wywozSmieciDetailsViewModel,
                onClick = {dateString ->
                    if (dateString.isNotEmpty()) {
                        val startMillis = convertDateToMillis(dateString, "yyyy-MM-dd")
                        val endMillis = startMillis + 3600000 // 1 hour duration

                        createCalendarEventIntent(
                            context,
                            title = "Wywóz śmieci: $type",
                            location = detailName,
                            description = type,
                            startMillis = startMillis,
                            endMillis = endMillis
                        )
                    }
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Composable
fun GarbageTypeBox(
    garbageType: String,
    wywozSmieciDetailsViewModel: WywozSmieciDetailsViewModel,
    onClick: (String) -> Unit
) {
    val filteredDates by wywozSmieciDetailsViewModel.filteredDatesMap.collectAsState()
    val dates = filteredDates[garbageType] ?: emptyList()
    val datesString = dates.joinToString(", ")
//    wywozSmieciDetailsViewModel.filterDatesByGarbageType(garbageType)
//    val filteredDates by wywozSmieciDetailsViewModel.filteredDates.collectAsState(initial = listOf())

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(JasnyNiebieski)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                when (garbageType) {
                    "Bio zielone" -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_garbage_type_color),
                            contentDescription = garbageType,
                            tint = Zielony,
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                    "Pojemniki czarne" -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_garbage_type_color),
                            contentDescription = garbageType,
                            tint = Color.Black,
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                    "Popiół" -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_garbage_type_color),
                            contentDescription = garbageType,
                            tint = Fioletowy,
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                    "Selektywna zbiórka odpadów" -> {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_garbage_type_color),
                            contentDescription = garbageType,
                            tint = Zolty,
                            modifier = Modifier
                                .size(50.dp)
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        elevation = 4.dp,
                        backgroundColor = Bialy
                    ) {
                        Text(
                            text = garbageType,
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h6.copy(fontSize = 18.sp, fontWeight = FontWeight.Medium)
                        )
                    }
                    Text(
                        text = datesString,
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
                IconButton(
                    onClick = {
                        onClick(dates.firstOrNull() ?: "")
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_calendar),
                        contentDescription = garbageType,
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun convertDateToMillis(dateString: String, pattern: String): Long {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    val localDate = LocalDate.parse(dateString, formatter)
    val localDateTime = localDate.atStartOfDay()
    val instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()
    return instant.toEpochMilli()
}

fun createCalendarEventIntent(
    context: Context,
    title: String,
    location: String,
    description: String,
    startMillis: Long,
    endMillis: Long
) {
    val intent = Intent(Intent.ACTION_INSERT).apply {
        data = CalendarContract.Events.CONTENT_URI
        putExtra(CalendarContract.Events.TITLE, title)
        putExtra(CalendarContract.Events.EVENT_LOCATION, location)
        putExtra(CalendarContract.Events.DESCRIPTION, description)
        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startMillis)
        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endMillis)
        putExtra(CalendarContract.Events.ALL_DAY, false) // Set to true if it's an all-day event
    }
    context.startActivity(intent)
}