package com.behnamuix.popcorn.view.components.animnation

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.behnamuix.popcorn.R

@Composable
fun EmptyDataAnimation(size:Int) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_anim))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        modifier = Modifier.size(size.dp),
        composition = composition,
        progress = { progress },
    )

}