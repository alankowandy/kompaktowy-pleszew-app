package com.example.pleszew.miejsca_rozrywki.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pleszew.R
import com.example.pleszew.miejsca_rozrywki.data.details.LocationDetails
import com.example.pleszew.miejsca_rozrywki.domain.DetailsViewModel
import com.example.pleszew.ui.theme.JasnyNiebieski
import java.util.Locale

@Composable
fun MiejscaRozrywkiDetailsScreen(
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    locationId: String?
) {
    if (locationId != null) {
        detailsViewModel.getDetails(locationId)
    }

    val context = LocalContext.current

    val details by detailsViewModel.details.collectAsState(initial = listOf())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        if (!details.isNullOrEmpty()) {
            items(details!!) { detail ->
                EntertainmentPlaceDetailsItem(
                    place = detail,
                    locationId = locationId,
                    context = context
                )
            }
        }

    }
}


@Composable
fun EntertainmentPlaceDetailsItem(
    place: LocationDetails,
    locationId: String?,
    context: Context
) {
    @DrawableRes val icon: Int = when (locationId) {
        "1" -> { R.drawable.ic_kino_hel }
        "2" -> { R.drawable.ic_recycling }
        "3" -> { R.drawable.ic_muzeum_piekarstwa }
        "4" -> { R.drawable.ic_muzeum }
        "5" -> { R.drawable.ic_park }
        "6" -> { R.drawable.ic_basen }
        "7" -> { R.drawable.ic_bowling }
        "8" -> { R.drawable.ic_book }
        "9" -> { R.drawable.ic_amfiteatr }
        "10" -> { R.drawable.ic_muzeum }
        "11" -> { R.drawable.ic_sport }
        "12" -> { R.drawable.ic_silownia }
        "13" -> { R.drawable.ic_centrum_wspierania }
        "14" -> { R.drawable.ic_stadion_miejski }
        else -> { R.drawable.ic_bus }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = JasnyNiebieski,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = icon),
                        contentDescription = "location_icon",
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = place.locationName,
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.weight(1f),
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                if (!place.weekDay.isNullOrEmpty() && place.weekDay[0].isNotBlank()) {
                    OpeningHoursBox(
                        weekDay = place.weekDay,
                        openHours = place.openHours,
                        closeHours = place.closeHours
                    )
                }
                AddressBox(place = place)
                if (!place.locationPhone.isNullOrBlank()) {
                    InformationBox(
                        icon = R.drawable.ic_phone,
                        value = place.locationPhone,
                        onClick = {
                            dialPhoneNumber(
                                phoneNumber = place.locationPhone,
                                context = context
                            )
                        }
                    )
                }
                if (!place.locationEmail.isNullOrBlank()) {
                    InformationBox(
                        icon = R.drawable.ic_email,
                        value = place.locationEmail,
                        onClick = {
                            composeEmail(
                                address = place.locationEmail,
                                context = context
                            )
                        }
                    )
                }
                if (!place.locationWebsite.isNullOrEmpty() && place.locationWebsite[0].isNotBlank()) {
                    place.locationWebsiteName?.forEach {website ->
                        when (website) {
                            "instagram" -> {
                                val index = place.locationWebsiteName.indexOf(website)
                                InformationBox(
                                    icon = R.drawable.ic_phone,
                                    value = place.locationWebsiteName[index].replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(
                                            Locale.ROOT
                                        ) else it.toString()
                                    },
                                    onClick = {
                                        openWebPage(
                                            url = place.locationWebsite[index],
                                            context = context
                                        )
                                    }
                                )
                            }
                            "facebook" -> {
                                val index = place.locationWebsiteName.indexOf(website)
                                InformationBox(
                                    icon = R.drawable.ic_phone,
                                    value = place.locationWebsiteName[index].replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(
                                            Locale.ROOT
                                        ) else it.toString()
                                    },
                                    onClick = {
                                        openWebPage(
                                            url = place.locationWebsite[index],
                                            context = context
                                        )
                                    }
                                )
                            }
                            "youtube" -> {
                            val index = place.locationWebsiteName.indexOf(website)
                            InformationBox(
                                icon = R.drawable.ic_phone,
                                value = place.locationWebsiteName[index].replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.ROOT
                                    ) else it.toString()
                                },
                                onClick = {
                                    openWebPage(
                                        url = place.locationWebsite[index],
                                        context = context
                                    )
                                }
                            )
                            }
                            else -> {
                                val index = place.locationWebsiteName.indexOf(website)
                                InformationBox(
                                    icon = R.drawable.ic_phone,
                                    value = place.locationWebsiteName[index],
                                    onClick = {
                                        openWebPage(
                                            url = place.locationWebsite[index],
                                            context = context
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OpeningHoursBox(
    weekDay: List<String>?,
    openHours: List<String>?,
    closeHours: List<String>?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Absolute.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Godziny otwarcia",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold,
                    fontSize = 19.sp,
                    color = Color.Black
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (weekDay != null) {
                    for (i in weekDay.indices) {
                        Row(
                            horizontalArrangement = Arrangement.Absolute.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Text(
                                text = weekDay[i],
                                style = MaterialTheme.typography.body1
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            if (openHours?.get(i) != "") {
                                Text(
                                    text = (openHours?.get(i) + " - ") ?: "Brak danych",
                                    style = MaterialTheme.typography.body1
                                )
                                Text(
                                    text = (closeHours?.get(i)) ?: "",
                                    style = MaterialTheme.typography.body1
                                )
                            } else {
                                Text(
                                    text = "Zamknięte",
                                    style = MaterialTheme.typography.body1
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

@Composable
fun AddressBox(
    place: LocationDetails
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Adres",
                    style = MaterialTheme.typography.subtitle1.copy(
                        fontWeight = FontWeight.Bold,
                        fontSize = 19.sp
                    ),
                    color = Color.Black
                )
                Text(
                    text = buildString {
                        append("${place.locationStreet} ${place.locationStreetNum}")
                        if (!place.locationHouseNum.isNullOrBlank()) {
                            append("/${place.locationHouseNum}")
                        }
                    },
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
                Text(
                    text = "${place.locationPostal} ${place.location}",
                    style = MaterialTheme.typography.body1,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun InformationBox(
    icon: Int,
    value: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 10.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(15.dp))
                Text(
                    text = value,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable {
                            onClick()
                        }
                )
            }
        }
    }
}

fun dialPhoneNumber(
    phoneNumber: String,
    context: Context
) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = Uri.parse("tel:$phoneNumber")
    }
    context.startActivity(intent)
}

fun composeEmail(
    address: String?,
    context: Context
) {
    val intent = Intent(Intent.ACTION_SENDTO).apply {
        data = Uri.parse("mailto:")
        putExtra(Intent.EXTRA_EMAIL, arrayOf(address))
    }
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    context.startActivity(intent)
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