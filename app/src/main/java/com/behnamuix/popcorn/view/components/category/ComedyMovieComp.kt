package com.behnamuix.popcorn.view.components.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.behnamuix.popcorn.view.components.items.RowDefaultListMovieItemCardComp
import com.behnamuix.popcorn.view.navigation.voyager.FullComedyMovieSc
import com.behnamuix.popcorn.view.viewmodels.category.ComedyGenreViewModel

@Composable
fun ComedyMovieComp() {
    var nav= LocalNavigator.currentOrThrow
    var comedyGenreViewModel: ComedyGenreViewModel = koinViewModel()
    val comedyMovie = comedyGenreViewModel.comedyMovie(35).collectAsLazyPagingItems()

    Column(
        modifier = Modifier.padding(top = 24.dp, end = 16.dp, start = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.clickable {  nav.parent?.push(FullComedyMovieSc)},

                color = MaterialTheme.colorScheme.primary,
                text = "دیدن همه",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(Modifier.weight(1f))
            Text(

                text = "کمدی",
                style = MaterialTheme.typography.bodyLarge
            )

        }
        LazyRow(reverseLayout = true) {
            items(comedyMovie.itemCount.coerceAtMost(20)) { index ->
                var comedy = comedyMovie[index]
                comedy?.let {
                    RowDefaultListMovieItemCardComp(
                        image = comedy.posterPath.toString(),
                        title = comedy.title,
                        genre = comedy.genreIds,
                        overview = comedy.overview,
                        vote = comedy.voteAverage,
                        id = comedy.id
                    )
                }

            }
        }

    }
}