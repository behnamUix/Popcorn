package com.behnamuix.popcorn.view.components

import androidx.compose.animation.core.EaseInOutQuad
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarouselComp() {
    data class CarouselItem(
        val id: Int,
        val imageAddr: String,
        val contentDescription: String
    )
    var item = remember {
        listOf(
            CarouselItem(
                0,
                "https://static.wikia.nocookie.net/netflix/images/0/0e/MH_S5_Promotional.jpg/revision/latest/scale-to-width-down/1200?cb=20210904021400",
                "سرقت پول"
            ),
            CarouselItem(
                1,
                "https://m.media-amazon.com/images/M/MV5BMzU5ZGYzNmQtMTdhYy00OGRiLTg0NmQtYjVjNzliZTg1ZGE4XkEyXkFqcGc@._V1_.jpg",
                "بریکینگ بد"
            ),
            CarouselItem(
                2,
                "https://miro.medium.com/v2/1*tqAD_RD1jRbU7sTIRzo2RQ.jpeg",
                "مستر ربات"
            ),
            CarouselItem(
                3,
                "https://medievalists.gumlet.io/wp-content/uploads/2015/11/Ragnar-Lothbrok-Vikings.jpg?format=webp&compress=true&quality=80&w=376&dpr=2.6",
                "وایکینگ"
            ),
        )
    }
    val pagerState = rememberPagerState(pageCount = { item.size })

    LaunchedEffect(pagerState) {
        while (true) {
            delay(4000)
            val nextPage = (pagerState.currentPage + 1) % item.size
            pagerState.animateScrollToPage(
                page = nextPage,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

        }
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        HorizontalPager(
            modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 16.dp),
            state = pagerState
        ) { page ->

            Column {
                AsyncImage(
                    model = item[page].imageAddr,
                    contentDescription = item[page].contentDescription,
                    modifier = Modifier
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    contentScale = ContentScale.Crop
                )
            }


        }



        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(pagerState.currentPage + 1) {

                Card(
                    colors = CardDefaults.cardColors(Color.White.copy(alpha = 0.5f)),
                    modifier = Modifier
                        .height(2.dp)
                        .width(50.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {

                }

            }
        }


    }
}