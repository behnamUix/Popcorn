package com.behnamuix.popcorn.view.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.behnamuix.popcorn.ui.theme.spacing
import com.behnamuix.popcorn.utils.convertEngToPerGenre
import com.behnamuix.popcorn.view.navigation.voyager.DetailMovieSc

@Composable
fun ColumnMovieItemCardComp(
    onClick: () -> Unit,
    id:Int,
    image: String,
    title: String,
    overView: String,
    genre: List<Int>,
    vote: Double
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var nav= LocalNavigator.currentOrThrow
        var showDetail by remember { mutableStateOf(false) }
        OutlinedCard(
            modifier = Modifier
                .clickable {

                    showDetail=true
                }
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(MaterialTheme.spacing.medium)
        ) {
            Row(
                modifier = Modifier.padding(MaterialTheme.spacing.small),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .weight(1f)
                        .padding((MaterialTheme.spacing.small)),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = title,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.5f),
                        text = if (overView.isEmpty()) {
                            "بدون توضیحات!"
                        } else {
                            overView
                        },
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = convertEngToPerGenre(genre),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Card(
                            shape = RoundedCornerShape(6.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)
                        ) {
                            Text(
                                modifier = Modifier.padding(4.dp),
                                text = "IMDB: ${"%.1f".format(vote.toFloat())}",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.Black
                            )

                        }
                    }
                }
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w780/$image",
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .size(150.dp)
                        .padding(4.dp)
                        .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
                )
            if(showDetail){
                onClick()
                nav.parent?.push(DetailMovieSc(id))
            }

            }
        }


    }

}