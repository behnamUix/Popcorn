package com.behnamuix.popcorn.view.navigation.voyager

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.behnamuix.popcorn.ui.theme.spacing
import com.behnamuix.popcorn.utils.convertEngToPerGenre
import com.behnamuix.popcorn.view.components.LoadingDataComp
import com.behnamuix.popcorn.view.components.items.ColumnMovieItemCardComp
import com.behnamuix.popcorn.view.viewmodels.MovieByGenreViewModel
import org.koin.androidx.compose.koinViewModel


@Composable
fun MovieByGenreComp(genreId: Int) {
    var nav= LocalNavigator.currentOrThrow
    val movieByGenreViewModel: MovieByGenreViewModel = koinViewModel()
    val movies = movieByGenreViewModel.categoryMovie(genreId).collectAsLazyPagingItems()
    Column(modifier = Modifier.padding(MaterialTheme.spacing.medium)) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.small),
            style = MaterialTheme.typography.bodyLarge,
            text = "ژانر ${convertEngToPerGenre(listOf(genreId))}"
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(MaterialTheme.spacing.medium)
        ) {

            if (movies.itemCount.equals(0)) {
                item {
                    LoadingDataComp()
                }
            } else {
                items(movies.itemCount) { index ->
                    val movie = movies[index]
                    movie?.let {
                        ColumnMovieItemCardComp(
                            onClick = {nav.parent?.push(DetailMovieSc(it.id)) } ,
                            id = movie.id,
                            image = movie.posterPath,
                            title = movie.title,
                            overView = movie.overview,
                            genre = movie.genreIds,
                            vote = movie.voteAverage
                        )
                    }

                }
            }
            if (movies.loadState.append is androidx.paging.LoadState.Loading) {
                item {
                    Text(
                        color = MaterialTheme.colorScheme.primary.copy(0.5f),
                        modifier = Modifier.fillMaxWidth(),
                        text = "در حال بارگذاری صفحه بعدی...",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }

            if (movies.loadState.append is androidx.paging.LoadState.Error) {
                item { Text("خطا در بارگذاری داده‌ها", style = MaterialTheme.typography.bodySmall) }
            }
        }
    }
}

