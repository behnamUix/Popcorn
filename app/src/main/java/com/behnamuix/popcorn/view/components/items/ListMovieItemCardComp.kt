package com.behnamuix.popcorn.view.components.items

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.behnamuix.popcorn.ui.theme.spacing

@Composable
fun ListMovieItemCardComp(
    image: String,
    title: String,
    genre: List<Int>,
    vote: Double
) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = "https://image.tmdb.org/t/p/w780/${image}",
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(150.dp)
                .padding(4.dp)
                .clip(RoundedCornerShape(MaterialTheme.spacing.medium))
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = title,
            maxLines = 2,
            style = MaterialTheme.typography.bodySmall
        )
    }


}