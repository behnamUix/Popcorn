package com.behnamuix.popcorn.view.components.items

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.behnamuix.popcorn.R
import com.behnamuix.popcorn.soundeffect.SoundPlayer
import com.behnamuix.popcorn.ui.theme.spacing
import com.behnamuix.popcorn.utils.convertEngToPerGenre

@Composable
fun FavoriteMovieItemCardComp(
    imdbId:String,
    image: String,
    title: String,
    overView: String,
    genre: List<Int>,
    vote: Double,
    removeItemClick:()->Unit
) {
    var ctx= LocalContext.current
    val player = SoundPlayer(ctx)
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedCard(
            modifier = Modifier

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
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = {
                            //sound effect play mishe!
                            player.play()
                            Toast.makeText(ctx,"از لیست علاقه مندی ها حذف شد", Toast.LENGTH_SHORT).show()
                            removeItemClick()

                        }) {
                            Icon(

                                painter = painterResource(R.drawable.icon_trash),
                                contentDescription = "",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                        Spacer(Modifier.weight(1f))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = title,
                            style = MaterialTheme.typography.titleSmall
                        )
                    }

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(0.8f),
                        text = if (overView.isEmpty()) {
                            "بدون توضیحات!"
                        } else {
                            overView
                        },
                        style = MaterialTheme.typography.bodySmall,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )

                    Row (
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
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
                        Text(
                            maxLines = 2,
                            modifier = Modifier
                                .weight(1.5f)
                                .alpha(0.5f),
                            text = convertEngToPerGenre(genre),
                            style = MaterialTheme.typography.labelSmall
                        )

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



            }
        }


    }

}