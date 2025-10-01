package com.behnamuix.popcorn.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.behnamuix.popcorn.ui.theme.spacing
import com.behnamuix.popcorn.view.components.items.ColumnMovieItemCardComp
import com.behnamuix.popcorn.view.navigation.voyager.DetailMovieSc
import com.behnamuix.popcorn.view.viewmodels.SearchViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchBarComp() {
    var nav= LocalNavigator.currentOrThrow
    var text by rememberSaveable { mutableStateOf("") }
    var resultNum: Int by rememberSaveable { mutableIntStateOf(0) }

    val searchViewModel: SearchViewModel = koinViewModel()
    val movies = searchViewModel.searchMovie(text).collectAsLazyPagingItems()
    Column(
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        modifier = Modifier
            .fillMaxSize()
            .padding(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            if (!resultNum.equals(0)) {
                Text(
                    text = "$resultNum مورد پیدا شده ",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Spacer(Modifier.weight(1f))
            Text(
                text = "جستجو",
                style = MaterialTheme.typography.bodyLarge
            )
        }
        OutlinedTextField(
            colors = OutlinedTextFieldDefaults.colors(

            ),
            maxLines = 1,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "جستجو فیلم و سریال",
                    style = MaterialTheme.typography.bodySmall
                )
            },
            value = text,
            onValueChange = {
                text = it
                searchViewModel.searchMovie(query = it)
            },
            textStyle = MaterialTheme.typography.bodySmall,
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 24.dp,
                bottomEnd = 24.dp,
                bottomStart = 24.dp
            )
        )


        LazyColumn(Modifier.height(800.dp)) {
            items(movies.itemCount) { index ->
                val movie = movies[index]
                movie?.let {
                    ColumnMovieItemCardComp(
                        onClick = {nav.parent?.push(DetailMovieSc(it.id)) } ,
                        id = it.id,
                        image = it.poster_path ?: "",
                        title = it.title ?: "",
                        overView = it.overview ?: "",
                        genre = it.genre_ids ?: emptyList(),
                        vote = it.vote_average ?: 1.0

                    )
                }
            }

            movies.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item {

                            Text(
                                text = "در حال بارگذاری...",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    loadState.append is LoadState.Loading -> {
                        item { Text("صفحه بعدی...", style = MaterialTheme.typography.bodySmall) }
                    }

                    loadState.append is LoadState.Error -> {
                        item {
                            Text(
                                "خطا در بارگذاری!",
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }
                }
            }
        }


    }
}
