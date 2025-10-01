package com.behnamuix.popcorn.view.components.category

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.behnamuix.popcorn.R
import com.behnamuix.popcorn.view.navigation.voyager.DetailMovieSc
import com.behnamuix.popcorn.view.viewmodels.category.LatestViewModel

@Composable
fun LatestMovieComp() {
    val latestViewModel: LatestViewModel = koinViewModel()
    val latest by latestViewModel.latest.collectAsState()
    var showDetail by remember { mutableStateOf(false) }
    val nav= LocalNavigator.currentOrThrow
    LaunchedEffect(latest) {
        latestViewModel.repo.getLatest()
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier.padding(16.dp)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                color = MaterialTheme.colorScheme.primary,
                text = "",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(Modifier.weight(1f))
            Text(

                text = "جدیدترین ها",
                style = MaterialTheme.typography.bodyLarge
            )

        }
        Log.d("debug",latest?.title.toString())
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp).fillMaxWidth().clickable { showDetail=true },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val posterUrl = latest?.posterPath
            if (posterUrl.isNullOrEmpty()) {
                OutlinedCard() {
                    Image(
                        painter = painterResource(R.drawable.popcorn_logo),
                        contentDescription = null,
                        contentScale = ContentScale.Inside,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .clip(RoundedCornerShape(16.dp))

                    )
                }
            } else {
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/$posterUrl",
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .size(200.dp)
                )
            }

                val title = latest?.title
                Text(text = title ?: "هنوز اعلام نشده")

                val voteCount = latest?.voteCount
                Card(
                    shape = RoundedCornerShape(6.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)
                ) {
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = if (voteCount == null) {
                            "هنوز اعلام نشده"
                        } else {
                            "IMDB: ${"%.1f".format(voteCount.toDouble())}"
                        },
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Black
                    )
                }

        }
        if(showDetail){
            nav.parent?.push(DetailMovieSc(latest?.imdbId?.toInt() ?:0))
        }
    }


}
