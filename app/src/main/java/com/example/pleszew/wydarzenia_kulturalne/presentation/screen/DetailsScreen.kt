package com.example.pleszew.wydarzenia_kulturalne.presentation.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.pleszew.miejsca_rozrywki.data.details.LocationDetails
import com.example.pleszew.ui.theme.Bialy
import com.example.pleszew.ui.theme.JasnyNiebieski
import com.example.pleszew.wydarzenia_kulturalne.data.details.EventDetails
import com.example.pleszew.wydarzenia_kulturalne.data.main.Event
import com.example.pleszew.wydarzenia_kulturalne.presentation.viewmodel.DetailsViewModel
import com.example.pleszew.wydarzenia_kulturalne.presentation.viewmodel.WydarzeniaKulturalneViewModel

@Composable
fun WydarzeniaKulturalneDetailsScreen(
    eventId: String?,
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    val event = detailsViewModel.event.collectAsState(initial = listOf()).value

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (!event.isNullOrEmpty()) {
                items(event) {
                        eventItem ->
                    EventItem(event = eventItem)
                }
            }
        }
    }
}

@Composable
fun EventItem(
    event: EventDetails
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Card(
                modifier = Modifier
                    .padding(16.dp),
                shape = RoundedCornerShape(8.dp),
                backgroundColor = JasnyNiebieski,
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = event.eventDate,
                                style = MaterialTheme.typography.subtitle1,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            if (event.eventEnd.isNotBlank()) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 4.dp, start = 16.dp, end = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = buildString {
                                            append("${event.eventStart} - ${event.eventEnd}")
                                        },
                                        style = MaterialTheme.typography.subtitle1,
                                        fontSize = 15.sp
                                    )
                                }
                            } else {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 4.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = event.eventStart,
                                        style = MaterialTheme.typography.subtitle1,
                                        fontSize = 15.sp
                                    )
                                }
                            }
                        }
                    }
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(width = 100.dp, height = 80.dp)
                            .padding(top = 8.dp, start = 12.dp, end = 12.dp, bottom = 8.dp),
                        shape = RoundedCornerShape(8.dp),
                        backgroundColor = Bialy,
                        elevation = 4.dp
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = event.eventName,
                                style = androidx.compose.material3.MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                    EventAddressBox(event = event)
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            ImageZoom(event = event)
        }
    }
}

@Composable
fun EventAddressBox(
    event: EventDetails
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, start = 12.dp, end = 12.dp, bottom = 16.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp
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
                    append("${event.eventStreet} ${event.eventStreetNum}")
                    if (event.eventApartmentNum.isNotBlank()) {
                        append("/${event.eventApartmentNum}")
                    }
                },
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
            Text(
                text = "${event.eventPostal} ${event.eventTown}",
                style = MaterialTheme.typography.body1,
                color = Color.Black
            )
            if (event.eventDetails.isNotBlank()) {
                Text(
                    text = event.eventDetails,
                    style = MaterialTheme.typography.body1,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ImageZoom(
    event: EventDetails
) {
    var scale by remember {
        mutableStateOf(1f)
    }
    var offset by remember {
        mutableStateOf(Offset.Zero)
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1038f / 1467f)
    ) {
        val state = rememberTransformableState {
            zoomChange, panChange, rotationChange ->
            scale = (scale * zoomChange).coerceIn(1f, 3f)

            val extraWidth = (scale - 1) * constraints.maxWidth
            val extraHeight = (scale - 1) * constraints.maxHeight

            val maxX = extraWidth / 2
            val maxY = extraHeight / 2

            offset = Offset(
                x = (offset.x + scale * panChange.x).coerceIn(-maxX, maxX),
                y = (offset.y + scale * panChange.y).coerceIn(-maxY, maxY)
            )
        }
        Image(
            painter = rememberAsyncImagePainter(model = event.eventImage),
            contentDescription = "Event Image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp)
                .clip(RoundedCornerShape(8.dp))
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    translationX = offset.x
                    translationY = offset.y
                }
                .transformable(state)
        )
    }
}







