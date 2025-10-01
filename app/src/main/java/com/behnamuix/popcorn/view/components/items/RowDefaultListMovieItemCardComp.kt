package com.behnamuix.popcorn.view.components.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.behnamuix.popcorn.R
import com.behnamuix.popcorn.utils.convertEngToPerGenre
import com.behnamuix.popcorn.view.navigation.voyager.DetailMovieSc

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowDefaultListMovieItemCardComp(

    id: Int,
    image: String,
    title: String,
    overview: String,
    genre: List<Int>,
    vote: Double
) {
    var nav = LocalNavigator.currentOrThrow
    var goToDetail by remember { mutableStateOf(false) }
    Column(
        Modifier
            .padding(8.dp)
            .clickable {
                goToDetail = true
            }, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (goToDetail == true) {
            //be dalil error cas exceptio n tab to screen error
            //az parent estefade kardam
            nav.parent?.push(
                DetailMovieSc(
                    id
                )
            )
        }

        if (image == null) {
            Image(
                painter = painterResource(R.drawable.slide1),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .size(200.dp)
            )
        } else {
            Box(Modifier.fillMaxSize()) {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/${image}",
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .size(150.dp)
                )

                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {


                }


            }
        }

        Text(
            style = MaterialTheme.typography.titleSmall,
            text = if (title == null) {
                "هنوز اعلام نشده"
            } else {
                title
            }
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            OutlinedCard(

                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(width = 0.5.dp, color = MaterialTheme.colorScheme.onTertiary)
            ) {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = if (vote.toFloat() == null) {
                        "هنوز اعلام نشده"
                    } else {
                        "IMDB: ${"%.1f".format(vote.toFloat())}"
                    },
                    style = MaterialTheme.typography.labelSmall,
                )

            }

            Text(
                modifier = Modifier
                    .padding(4.dp)
                    .alpha(0.5f),
                text = if (genre == null) {
                    "هنوز اعلام نشده"
                } else {
                    convertEngToPerGenre(genre.take(2))
                },
                style = MaterialTheme.typography.labelSmall,

                )

        }

    }
}





