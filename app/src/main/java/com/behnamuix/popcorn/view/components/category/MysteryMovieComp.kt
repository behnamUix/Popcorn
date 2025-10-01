package com.behnamuix.popcorn.view.components.category

import androidx.compose.foundation.clickable
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
import com.behnamuix.popcorn.view.navigation.voyager.FullMysteryMovieSc
import com.behnamuix.popcorn.view.viewmodels.category.MysteryGenreViewModel

@Composable
fun MysteryMovieComp() {
    var nav= LocalNavigator.currentOrThrow

    var mysteryGenreViewModel: MysteryGenreViewModel = koinViewModel()
    val mysteryMovie = mysteryGenreViewModel.mysteryMovie(9648).collectAsLazyPagingItems()

    Column(
        modifier = Modifier.padding(top = 24.dp, end = 16.dp, start = 16.dp)

        ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.clickable {  nav.parent?.push(FullMysteryMovieSc) },
                color = MaterialTheme.colorScheme.primary,
                text = "دیدن همه",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(Modifier.weight(1f))
            Text(

                text = "معمایی",
                style = MaterialTheme.typography.bodyLarge
            )

        }
        LazyRow(reverseLayout = true) {
            items(count = mysteryMovie.itemCount.coerceAtMost(20)) {index->
                var mystery=mysteryMovie[index]
                mystery?.let {
                    RowDefaultListMovieItemCardComp(

                        id=it.id,
                        image = it.posterPath.toString(),
                        title = it.title,
                        overview = it.overview,
                        genre = it.genreIds,
                        vote = it.voteAverage
                    )
                }

            }
        }

    }
}