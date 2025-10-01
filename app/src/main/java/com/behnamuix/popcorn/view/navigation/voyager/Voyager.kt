package com.behnamuix.popcorn.view.navigation.voyager

import android.os.Build
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import cafe.adriel.voyager.navigator.tab.TabNavigator
import coil.compose.AsyncImage
import com.behnamuix.popcorn.R
import com.behnamuix.popcorn.database.room.entity.FavoritesMovieModel
import com.behnamuix.popcorn.ui.theme.spacing
import com.behnamuix.popcorn.utils.checkNetAndVpn
import com.behnamuix.popcorn.utils.convertJob
import com.behnamuix.popcorn.utils.convertLanguageCode
import com.behnamuix.popcorn.utils.convertMiladiToShamsi
import com.behnamuix.popcorn.utils.convertToHourFormat
import com.behnamuix.popcorn.view.components.LoadingDataComp
import com.behnamuix.popcorn.view.components.Yplayer.TrailerPlayer
import com.behnamuix.popcorn.view.components.items.ColumnMovieItemCardComp
import com.behnamuix.popcorn.view.components.toolbar.ToolbarComp
import com.behnamuix.popcorn.view.viewmodels.category.FavoriteViewModel
import com.behnamuix.popcorn.view.viewmodels.MovieActorByIdViewModel
import com.behnamuix.popcorn.view.viewmodels.MovieDetailByIdViewModel
import com.behnamuix.popcorn.view.viewmodels.TrailerMovieViewModel
import com.behnamuix.popcorn.view.viewmodels.category.ComedyGenreViewModel
import com.behnamuix.popcorn.view.viewmodels.category.FearGenreViewModel
import com.behnamuix.popcorn.view.viewmodels.category.LoveGenreViewModel
import com.behnamuix.popcorn.view.viewmodels.category.MysteryGenreViewModel
import com.behnamuix.popcorn.view.viewmodels.category.WarGenreViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.text.NumberFormat
import java.util.Locale

object SplashSc : Screen {
    @Composable
    override fun Content() {
        val localNav = LocalNavigator.currentOrThrow
        val ctx = LocalContext.current

        var showError by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            if (checkNetAndVpn(ctx)) {
                delay(4000)
                localNav.replace(HomeSc)
            } else {
                showError = true
            }
        }

        Box(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = painterResource(R.drawable.img_splash_background),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 80.dp),
                contentAlignment = Alignment.BottomCenter
            ) {
                Column(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "پاپکورن",
                        color = MaterialTheme.colorScheme.onBackground,
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Text(
                        text = "فیلم بعدی با پاپ\u200Cکورن خوشمزه\u200Cتره",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f),
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(8.dp))

                    LinearProgressIndicator(
                        modifier = Modifier
                            .height(18.dp)
                            .padding(
                                top = MaterialTheme.spacing.large,
                                bottom = MaterialTheme.spacing.large
                            )
                    )

                    if (showError) {
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedCard(modifier = Modifier.fillMaxWidth(0.8f)) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                    maxLines = 2,
                                    text = " برای عملکرد برنامه اینترنت و VPN باید روشن باشد سپس بر روی دکمه زیر ضربه بزنید!",
                                    color = MaterialTheme.colorScheme.error.copy(alpha = 0.8f),
                                    style = MaterialTheme.typography.bodySmall
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                OutlinedButton(
                                    shape = RoundedCornerShape(8.dp),
                                    onClick = {
                                        if (checkNetAndVpn(ctx)) {
                                            showError = false
                                            localNav.replace(HomeSc)
                                        } else {
                                            Toast.makeText(
                                                ctx,
                                                "وی پی ان فعال نیست!",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }) {
                                    Text("تلاش دوباره")
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}

object HomeSc : Screen {
    @Composable
    override fun Content() {
        TabNavigator(HomeTab) { tabnav ->
            Scaffold(
                topBar = { ToolbarComp(R.drawable.icon_notif, onIconClick = {}) },
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    NavigationBar(

                        modifier = Modifier
                            .clip(
                                RoundedCornerShape(
                                    topStart = 24.dp,
                                    topEnd = 24.dp
                                )
                            )
                            .border(
                                width = 0.2.dp,
                                brush = Brush.verticalGradient(
                                    listOf(
                                        MaterialTheme.colorScheme.primary,
                                        Color.Transparent
                                    )
                                ),
                                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
                            ),
                        containerColor = MaterialTheme.colorScheme.background
                    ) {
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                selectedIconColor = MaterialTheme.colorScheme.primary
                            ),
                            selected = tabnav.current == HomeTab,
                            onClick = {
                                tabnav.current = HomeTab
                            },
                            label = {
                                Text(
                                    text = HomeTab.options.title,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            icon = {
                                Icon(
                                    painter = HomeTab.options.icon!!,
                                    contentDescription = ""
                                )
                            })
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                selectedIconColor = MaterialTheme.colorScheme.primary
                            ),
                            selected = tabnav.current == FavoriteTab,
                            onClick = {
                                tabnav.current = FavoriteTab
                            },
                            label = {
                                Text(
                                    text = FavoriteTab.options.title,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            icon = {
                                Icon(
                                    painter = FavoriteTab.options.icon!!,
                                    contentDescription = ""
                                )
                            })
                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                selectedIconColor = MaterialTheme.colorScheme.primary
                            ),
                            selected = tabnav.current == SearchTab,
                            onClick = {
                                tabnav.current = SearchTab
                            },
                            label = {
                                Text(
                                    text = SearchTab.options.title,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            icon = {
                                Icon(
                                    painter = SearchTab.options.icon!!,
                                    contentDescription = ""
                                )
                            })
                    }
                }
            ) { innerPadding ->
                Box(
                    Modifier
                        .padding(innerPadding)
                        .background(MaterialTheme.colorScheme.background)
                ) {

                    tabnav.current.Content()
                }
            }
        }

    }
}

class DetailMovieSc(private val id: Int) : Screen {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        var showTrailer by rememberSaveable { mutableStateOf(false) }
        var key by remember { mutableStateOf("") }

        val favoriteViewModel: FavoriteViewModel = koinViewModel()
        val trailerMovieViewModel: TrailerMovieViewModel = koinViewModel()
        val fullDetailByIdViewModel: MovieDetailByIdViewModel = koinViewModel()
        val movieActorByIdViewModel: MovieActorByIdViewModel = koinViewModel()

        val listDetail by fullDetailByIdViewModel.deatilMovie.collectAsState()
        val trailer by trailerMovieViewModel.trailerMovie.collectAsState()
        val listCast by movieActorByIdViewModel.castMovie.collectAsState()
        val listCrew by movieActorByIdViewModel.crewMovie.collectAsState()
        val ctx = LocalContext.current
        val nav = LocalNavigator.currentOrThrow
        val sheetState = rememberModalBottomSheetState(
            skipPartiallyExpanded = false,
            confirmValueChange = { newValue ->
//baraye inke bottomsheet hichjoore baste nashe!
                newValue != SheetValue.Hidden
            }
        )
        val detailRow = listOf(
            listDetail?.production_companies?.getOrNull(1)?.name.toString(),
            convertToHourFormat(listDetail?.runtime ?: 0),
            convertLanguageCode(listDetail?.original_language ?: "en"),
            NumberFormat.getNumberInstance(Locale("fa", "IR"))
                .format(listDetail?.budget ?: 0)
        )
        val iconList = listOf(
            R.drawable.icon_corp,
            R.drawable.icon_clock,
            R.drawable.icon_lang,
            R.drawable.icon_money
        )
        LaunchedEffect(id) {
            trailerMovieViewModel.getTrailer(id)
            fullDetailByIdViewModel.loadFullDetailByImdb(id)
            movieActorByIdViewModel.loadCastDetail(id)
            movieActorByIdViewModel.loadCrewDetail(id)
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {


            if (listDetail == null) {
                LoadingDataComp()
            } else {
// Image
                AsyncImage(
                    model = "https://image.tmdb.org/t/p/w500/${listDetail?.poster_path}",
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(32.dp))

                )

// Bottom Sheet
                ModalBottomSheet(
                    containerColor = Color.Black.copy(alpha = 0.8f),
                    scrimColor = Color.Transparent,
                    onDismissRequest = { nav.pop() },
                    sheetState = sheetState,

                    ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(MaterialTheme.spacing.medium)
                            .fillMaxWidth()


                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            IconButton(
                                modifier = Modifier
                                    .padding(MaterialTheme.spacing.small)
                                    .clip(CircleShape),
                                onClick = {
                                    nav.pop()

                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_arrow_back),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier
                                        .size(24.dp)

                                )
                            }
                            Spacer(Modifier.weight(1f))
                            Text(
                                style = MaterialTheme.typography.headlineMedium,
                                text = listDetail?.title ?: "",
                                color = Color.White
                            )
                            Spacer(Modifier.weight(1f))
                            IconButton(
                                modifier = Modifier
                                    .padding(MaterialTheme.spacing.small)
                                    .clip(CircleShape),
                                onClick = {
                                    Toast.makeText(
                                        ctx,
                                        "به لیست علاقه‌مندی‌ها اضافه شد",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    favoriteViewModel.addToFavorites(
                                        FavoritesMovieModel(
                                            imdbId = listDetail?.imdb_id
                                                ?.takeIf { it.startsWith("tt") }
                                                ?.drop(2)
                                                ?.toString()
                                                ?: "",  // پیش‌فرض 0 اگر null یا اشتباه بود
                                            title = listDetail?.title ?: "نامشخص",
                                            rate = listDetail?.vote_average ?: 0.0,
                                            image = listDetail?.poster_path ?: "",
                                            overview = listDetail?.overview ?: "",
                                            genre = listDetail?.genres?.map { it.id } ?: emptyList()
                                        )
                                    )

                                }
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.icon_favorites),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }


                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            Text(
                                style = MaterialTheme.typography.titleLarge,
                                text = "تاریخ انتشار: ${convertMiladiToShamsi(listDetail?.release_date ?: "")}",
                                color = Color.White.copy(alpha = 0.5f)
                            )
                        } else {
                            Text(
                                style = MaterialTheme.typography.titleLarge,
                                text = listDetail?.release_date ?: "",
                                color = Color.White.copy(alpha = 0.5f)
                            )
                        }
                        Text(

                            style = MaterialTheme.typography.titleLarge,
                            text = "درآمد: ${
                                NumberFormat.getNumberInstance(Locale("fa", "IR"))
                                    .format(listDetail?.revenue ?: "بدون درآمد") ?: ""
                            } $",
                            color = Color.White.copy(alpha = 0.5f)
                        )
                        OutlinedCard(

                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(
                                width = 0.5.dp,
                                color = MaterialTheme.colorScheme.onTertiary
                            )
                        ) {
                            Text(
                                modifier = Modifier.padding(4.dp),
                                text = if (listDetail?.vote_average == null) {
                                    "هنوز اعلام نشده"
                                } else {
                                    "IMDB: ${"%.1f".format(listDetail?.vote_average)}"
                                },
                                style = MaterialTheme.typography.bodyLarge,
                            )

                        }
                        HorizontalDivider(
                            Modifier
                                .fillMaxWidth(0.8f)
                                .padding(MaterialTheme.spacing.small),
                            thickness = 1.dp,
                            color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                        )
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .horizontalScroll(rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {


// با استفاده از index
                            detailRow.forEachIndexed { index, text ->
                                OutlinedCard(
                                    modifier = Modifier.padding(4.dp),
                                    colors = CardDefaults.outlinedCardColors(
                                        containerColor = Color.Transparent
                                    ),
                                    border = BorderStroke(
                                        width = 0.5.dp,
                                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f)
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier.padding(4.dp),

                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            painter = painterResource(iconList.getOrElse(index) { R.drawable.icon_home }),
                                            contentDescription = "",
                                            tint = MaterialTheme.colorScheme.onPrimary,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            text = text ?: "",
                                            color = MaterialTheme.colorScheme.onPrimary,
                                            modifier = Modifier.padding(8.dp),
                                            style = MaterialTheme.typography.bodySmall,
                                        )
                                    }
                                }
                            }
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier =
                                Modifier
                                    .padding(MaterialTheme.spacing.medium)
                                    .verticalScroll(
                                        rememberScrollState()
                                    )
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
//Trailer
                                Text(
                                    modifier = Modifier.weight(1f),
                                    text = "پیش نمایش :",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.titleMedium,

                                    )
                                Icon(
                                    painter = painterResource(R.drawable.icon_video),
                                    contentDescription = "",
                                    tint = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.size(24.dp)

                                )
                            }
                            if (trailer.isEmpty()) {
                                Text(
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth(),
                                    style = MaterialTheme.typography.titleSmall,
                                    text = "هیچ پیش نمایشی پیدا نشد",
                                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
                                )
                            } else {
                                key = trailer.first().key
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    OutlinedCard(
                                        modifier = Modifier.clickable {
                                            showTrailer = !showTrailer
                                        }
                                    ) {
                                        Column(
                                            verticalArrangement = Arrangement.spacedBy(8.dp),
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Text(
                                                text = "پیش نمایش (یوتیوب) ",
                                                color = MaterialTheme.colorScheme.onPrimary.copy(
                                                    alpha = 0.5f
                                                ),
                                                style = MaterialTheme.typography.bodySmall,

                                                )


                                        }

                                    }


                                }



                                if (showTrailer) {
                                    var trailerUrl = "https://www.youtube.com/watch?v=$key"
                                    Card(modifier = Modifier
                                        .fillMaxWidth()
                                        .height(80.dp)) {
                                        TrailerPlayer(trailerUrl)
                                    }


                                }

                            }


//Story
                            Text(
                                text = "خلاصه داستان:",
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.titleMedium,

                                )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = if (listDetail?.overview.toString().isEmpty()) {
                                    "بدون داستان"
                                } else {
                                    listDetail?.overview.toString()
                                },
                                color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.5f),
                                style = MaterialTheme.typography.titleSmall,

                                )
//Actors
                            Text(
                                text = "بازیگران",
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.titleMedium,

                                )
                            LazyRow(Modifier.fillMaxHeight()) {
                                items(items = listCast) {

                                    Column(
                                        modifier = Modifier.padding(4.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {

                                        AsyncImage(
                                            model = "https://image.tmdb.org/t/p/w200/${it.profilePath}",
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .border(
                                                    width = 1.dp,
                                                    color = MaterialTheme.colorScheme.primary.copy(
                                                        alpha = 0.5f
                                                    ),
                                                    shape = CircleShape
                                                )
                                                .size(80.dp)
                                                .clip(CircleShape)
                                        )
                                        Text(text = "(${it.character})", color = Color.White)
                                        Text(text = it.name, color = Color.White)
                                    }

                                }
                            }
                            Text(
                                text = "عوامل پشت صحنه",
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.fillMaxWidth(),
                                style = MaterialTheme.typography.titleMedium,

                                )
                            LazyRow(Modifier.fillMaxHeight()) {
                                items(items = listCrew) {

                                    Column(
                                        modifier = Modifier.padding(4.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        AsyncImage(
                                            model = "https://image.tmdb.org/t/p/w200/${it.profilePath}",
                                            contentDescription = "",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .border(
                                                    width = 1.dp,
                                                    color = MaterialTheme.colorScheme.primary.copy(
                                                        alpha = 0.5f
                                                    ),
                                                    shape = CircleShape
                                                )
                                                .size(80.dp)
                                                .clip(CircleShape)
                                        )
                                        Text(text = "(${convertJob(it.job)})", color = Color.White)
                                        Text(text = it.name, color = Color.White)
                                    }

                                }
                            }


                        }

                    }
                    BackHandler {
                        if (sheetState.isVisible) {

                        } else {
                            nav.pop()
                        }
                    }
                }
            }
        }
    }
}


//AllDataCategoryScreens:
object FullLoveMovieSc : Screen {
    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow
        val loveGenreViewModel: LoveGenreViewModel = koinViewModel()
        val loveMovie = loveGenreViewModel.loveMovie(10749).collectAsLazyPagingItems()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ToolbarComp(R.drawable.icon_arrow_back, onIconClick = { nav.pop() })

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("", color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "عاشقانه",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(loveMovie.itemCount) { index ->
                        val love = loveMovie[index]
                        love?.let {
                            ColumnMovieItemCardComp(
                                onClick = { nav.push(DetailMovieSc(it.id)) },
                                id = it.id,
                                image = it.posterPath ?: "",
                                title = it.title ?: "",
                                overView = it.overview ?: "",
                                genre = it.genreIds ?: emptyList(),
                                vote = it.voteAverage ?: 1.0
                            )
                        }
                    }

                    loveMovie.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> item {
                                Text(
                                    "در حال بارگذاری...",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            loadState.append is LoadState.Loading -> item {
                                Text("صفحه بعدی...", style = MaterialTheme.typography.bodySmall)
                            }

                            loadState.append is LoadState.Error -> item {
                                Text("خطا در بارگذاری!", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}

object FullWarMovieSc : Screen {
    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow
        val warGenreViewModel: WarGenreViewModel = koinViewModel()
        val warMovie = warGenreViewModel.warMovie(10752).collectAsLazyPagingItems()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ToolbarComp(R.drawable.icon_arrow_back, onIconClick = { nav.pop() })

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("", color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "جنگی",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(warMovie.itemCount) { index ->
                        val war = warMovie[index]
                        war?.let {
                            ColumnMovieItemCardComp(
                                onClick = { nav.push(DetailMovieSc(it.id)) },

                                id = it.id,
                                image = it.posterPath ?: "",
                                title = it.title ?: "",
                                overView = it.overview ?: "",
                                genre = it.genreIds ?: emptyList(),
                                vote = it.voteAverage ?: 1.0
                            )
                        }
                    }

                    warMovie.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> item {
                                Text(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    text = "در حال بارگذاری...",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            loadState.append is LoadState.Loading -> item {
                                Text(
                                    color = MaterialTheme.colorScheme.onPrimary,

                                    text = "صفحه بعدی...",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            loadState.append is LoadState.Error -> item {
                                Text("خطا در بارگذاری!", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}

object FullFearMovieSc : Screen {
    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow
        val fearGenreViewModel: FearGenreViewModel = koinViewModel()
        val fearMovie = fearGenreViewModel.fearMovie(53).collectAsLazyPagingItems()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ToolbarComp(R.drawable.icon_arrow_back, onIconClick = { nav.pop() })

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("", color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "ترسناک",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(fearMovie.itemCount) { index ->
                        val fear = fearMovie[index]
                        fear?.let {
                            ColumnMovieItemCardComp(
                                onClick = { nav.push(DetailMovieSc(it.id)) },

                                id = it.id,
                                image = it.posterPath ?: "",
                                title = it.title ?: "",
                                overView = it.overview ?: "",
                                genre = it.genreIds ?: emptyList(),
                                vote = it.voteAverage ?: 1.0
                            )
                        }
                    }

                    fearMovie.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> item {
                                Text(
                                    color = MaterialTheme.colorScheme.onPrimary,

                                    text = "در حال بارگذاری...",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            loadState.append is LoadState.Loading -> item {
                                Text(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    text = "صفحه بعدی...",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            loadState.append is LoadState.Error -> item {
                                Text("خطا در بارگذاری!", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}

object FullMysteryMovieSc : Screen {
    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow
        val mysteryGenreViewModel: MysteryGenreViewModel = koinViewModel()
        val mysteryMovie = mysteryGenreViewModel.mysteryMovie(9648).collectAsLazyPagingItems()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ToolbarComp(R.drawable.icon_arrow_back, onIconClick = { nav.pop() })

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("", color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "معمایی",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(mysteryMovie.itemCount) { index ->
                        val mystery = mysteryMovie[index]
                        mystery?.let {
                            ColumnMovieItemCardComp(
                                onClick = { nav.push(DetailMovieSc(it.id)) },

                                id = it.id,
                                image = it.posterPath ?: "",
                                title = it.title ?: "",
                                overView = it.overview ?: "",
                                genre = it.genreIds ?: emptyList(),
                                vote = it.voteAverage ?: 1.0
                            )
                        }
                    }

                    mysteryMovie.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> item {
                                Text(
                                    color = MaterialTheme.colorScheme.onPrimary,

                                    text = "در حال بارگذاری...",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            loadState.append is LoadState.Loading -> item {
                                Text(
                                    color = MaterialTheme.colorScheme.onPrimary,

                                    text = "صفحه بعدی...",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            loadState.append is LoadState.Error -> item {
                                Text("خطا در بارگذاری!", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}

object FullComedyMovieSc : Screen {
    @Composable
    override fun Content() {
        val nav = LocalNavigator.currentOrThrow
        val comedyGenreViewModel: ComedyGenreViewModel = koinViewModel()
        val comedyMovie = comedyGenreViewModel.comedyMovie(35).collectAsLazyPagingItems()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ToolbarComp(R.drawable.icon_arrow_back, onIconClick = { nav.pop() })

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("", color = MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = "کمدی",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(comedyMovie.itemCount) { index ->
                        val comedy = comedyMovie[index]
                        comedy?.let {
                            ColumnMovieItemCardComp(
                                onClick = { nav.push(DetailMovieSc(it.id)) },

                                id = it.id,
                                image = it.posterPath ?: "",
                                title = it.title ?: "",
                                overView = it.overview ?: "",
                                genre = it.genreIds ?: emptyList(),
                                vote = it.voteAverage ?: 1.0
                            )
                        }
                    }

                    comedyMovie.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> item {
                                Text(
                                    color = MaterialTheme.colorScheme.onPrimary,

                                    text = "در حال بارگذاری...",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            loadState.append is LoadState.Loading -> item {
                                Text(
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    text = "صفحه بعدی...",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }

                            loadState.append is LoadState.Error -> item {
                                Text("خطا در بارگذاری!", style = MaterialTheme.typography.bodySmall)
                            }
                        }
                    }
                }
            }
        }
    }
}






