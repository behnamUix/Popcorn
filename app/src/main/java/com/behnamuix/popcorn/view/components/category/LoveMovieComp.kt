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
import com.behnamuix.popcorn.view.navigation.voyager.FullLoveMovieSc
import com.behnamuix.popcorn.view.viewmodels.category.LoveGenreViewModel

@Composable
fun LoveMovieComp() {
    var loveGenreViewModel: LoveGenreViewModel = koinViewModel()
    val loveMovie = loveGenreViewModel.loveMovie(10749).collectAsLazyPagingItems()
var nav= LocalNavigator.currentOrThrow
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)



    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.clickable {  nav.parent?.push(FullLoveMovieSc)},
                color = MaterialTheme.colorScheme.primary,
                text = "دیدن همه",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(Modifier.weight(1f))
            Text(

                text = "عاشقانه",
                style = MaterialTheme.typography.bodyLarge
            )

        }
        LazyRow(reverseLayout = true) {
            //Coer->faghat 20 tayeaval
            items(count = loveMovie.itemCount.coerceAtMost(20)) {index->
                var love=loveMovie[index]
                love?.let {
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