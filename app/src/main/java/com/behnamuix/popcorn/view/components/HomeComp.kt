package com.behnamuix.popcorn.view.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.behnamuix.popcorn.view.components.category.ComedyMovieComp
import com.behnamuix.popcorn.view.components.category.FearMovieComp
import com.behnamuix.popcorn.view.components.category.LatestMovieComp
import com.behnamuix.popcorn.view.components.category.LoveMovieComp
import com.behnamuix.popcorn.view.components.category.MysteryMovieComp
import com.behnamuix.popcorn.view.components.category.PopularMovieComp
import com.behnamuix.popcorn.view.components.category.WarMovieComp

@Composable
fun HomeComp() {

    val ctx = LocalContext.current

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        NotificationComp(ctx = ctx)
        CarouselComp()
        CategoryComp()
        PopularMovieComp()
        HorizontalLine()
        LatestMovieComp()
        MysteryMovieComp()
        ComedyMovieComp()
        FearMovieComp()
        WarMovieComp()
        LoveMovieComp()

    }
}

@Composable
fun HorizontalLine() {
    HorizontalDivider(
        Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .clip(RoundedCornerShape(8.dp)),
        thickness = 0.2.dp,
        color = MaterialTheme.colorScheme.onPrimary
    )
}

