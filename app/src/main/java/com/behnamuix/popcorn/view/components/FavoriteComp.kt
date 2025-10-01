package com.behnamuix.popcorn.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.behnamuix.popcorn.ui.theme.spacing
import com.behnamuix.popcorn.view.components.animnation.EmptyDataAnimation
import com.behnamuix.popcorn.view.components.items.FavoriteMovieItemCardComp
import com.behnamuix.popcorn.view.viewmodels.category.FavoriteViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoriteComp() {
    val favoriteViewModel: FavoriteViewModel = koinViewModel()
    val movies = favoriteViewModel.movies

    LaunchedEffect(Unit) {
        favoriteViewModel.loadFavorites()

    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium)
    ) {
        Row {
            if (movies.size > 0) {
                Text(

                    text = "${movies.size.toString()}مورد ",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(Modifier.weight(1f))

            Text(

                text = "علاقه مندی ها",
                style = MaterialTheme.typography.bodyLarge
            )

        }
        if (movies.isEmpty()) {
            Column(

                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmptyDataAnimation(250)
                Spacer(Modifier.height(16.dp))
                Text("هنوز چیزی به لیست علاقه مندیهات اضافه نکردی!")

            }
        } else {
            LazyColumn(reverseLayout = true) {
                items(items = movies) {
                    it.rate?.let { it1 ->
                        FavoriteMovieItemCardComp(
                            imdbId = it.imdbId ?: "",
                            removeItemClick = { favoriteViewModel.removeFromFavorites(it) },
                            image = it.image ?: "",
                            title = it.title.toString(),
                            overView = it.overview.toString(),
                            genre = it.genre,
                            vote = it1
                        )
                    }
                }
            }
        }

    }
}

