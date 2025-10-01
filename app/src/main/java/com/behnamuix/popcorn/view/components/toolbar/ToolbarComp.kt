package com.behnamuix.popcorn.view.components.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.behnamuix.popcorn.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarComp(icon:Int,onIconClick:()->Unit) {
    Column {
        TopAppBar(

            title = {

            },
            navigationIcon = {
                IconButton(onClick = {onIconClick() }) {
                   // NotificationAnimation()
                    Icon(
                        painter = painterResource(icon),
                        contentDescription = "Localized description",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            },
            actions = {
                Image(
                    modifier = Modifier.size(96.dp),
                    painter = painterResource(R.drawable.popcorn_logo),
                    contentDescription = "Logo",
                    contentScale = ContentScale.Crop
                )
            }
        )

    }
}