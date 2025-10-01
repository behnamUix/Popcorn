package com.behnamuix.popcorn.view.components.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil.compose.AsyncImage
import com.behnamuix.popcorn.R
import com.behnamuix.popcorn.database.room.entity.FavoritesMovieModel
import com.behnamuix.popcorn.ui.theme.spacing
import com.behnamuix.popcorn.utils.convertEngToPerGenre
import com.behnamuix.popcorn.view.components.LoadingDataComp
import com.behnamuix.popcorn.view.navigation.voyager.DetailMovieSc
import com.behnamuix.popcorn.view.viewmodels.category.FavoriteViewModel
import com.behnamuix.popcorn.view.viewmodels.category.PopularViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PopularMovieComp() {
    var nav= LocalNavigator.currentOrThrow
    val favoriteViewModel: FavoriteViewModel = koinViewModel()
    val popularViewModel: PopularViewModel = koinViewModel()
    val populars by popularViewModel.populars.collectAsState()
    populars.shuffled()
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                color = MaterialTheme.colorScheme.primary,
                text = "",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(Modifier.weight(1f))
            Text(

                text = "محبوب ترین ها",
                style = MaterialTheme.typography.bodyLarge
            )

        }

        if (populars == null) {
            LoadingDataComp()
        } else {

            HorizontalMultiBrowseCarousel(

                state = rememberCarouselState { populars.count() },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 16.dp, bottom = 16.dp),
                preferredItemWidth = 200.dp,
                itemSpacing = 8.dp,
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) { i ->
                val item = populars[i]
                Column(Modifier.padding(4.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(Modifier.fillMaxSize()) {
                        AsyncImage(
                            model = "https://image.tmdb.org/t/p/w500/${item.poster_path}",
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .clickable { nav.parent?.push(DetailMovieSc(item.id)) }
                                .clip(RoundedCornerShape(16.dp))
                                .size(250.dp)
                        )
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
                            IconButton(
                                modifier = Modifier
                                    .padding(MaterialTheme.spacing.small)
                                    .clip(CircleShape)
                                    .background(Color.Black.copy(alpha = 0.7f)),
                                onClick =
                                    {
                                        favoriteViewModel.addToFavorites(
                                            FavoritesMovieModel(
                                                title = item.title,
                                                rate = item.vote_average,
                                                image = item.poster_path,
                                                overview = item.overview,
                                                genre = item.genre_ids
                                            )
                                        )
                                    }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_favorites),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.onTertiary
                                )
                            }
                        }
                    }


                    Text(text = item.title, style = MaterialTheme.typography.titleSmall)
                    Text(
                        modifier = Modifier.alpha(0.5f),
                        text = convertEngToPerGenre(item.genre_ids),
                        style = MaterialTheme.typography.labelSmall
                    )
                }


            }
        }
    }
}