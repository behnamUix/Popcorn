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
import com.behnamuix.popcorn.view.navigation.voyager.FullWarMovieSc
import com.behnamuix.popcorn.view.viewmodels.category.WarGenreViewModel

@Composable
fun WarMovieComp() {
    var nav= LocalNavigator.currentOrThrow

    var warGenreViewModel: WarGenreViewModel = koinViewModel()
    val warMovie = warGenreViewModel.warMovie(10752).collectAsLazyPagingItems()

    Column(
        modifier = Modifier.padding(top = 24.dp, end = 16.dp, start = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.clickable {  nav.parent?.push(FullWarMovieSc)},

                color = MaterialTheme.colorScheme.primary,
                text = "دیدن همه",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(Modifier.weight(1f))
            Text(

                text = "جنگی",
                style = MaterialTheme.typography.bodyLarge
            )

        }
        LazyRow(reverseLayout = true) {
            items(count = warMovie.itemCount.coerceAtMost(20)) {index->
                var war=warMovie[index]
                war?.let{
                    RowDefaultListMovieItemCardComp(
                        image = it.posterPath.toString(),
                        title = it.title,
                        genre = it.genreIds,
                        overview = it.overview,
                        vote = it.voteAverage,
                        id = it.id
                    )
                }


            }
        }

    }
}