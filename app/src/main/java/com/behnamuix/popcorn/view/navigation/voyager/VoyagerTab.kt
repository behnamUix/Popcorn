package com.behnamuix.popcorn.view.navigation.voyager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.behnamuix.popcorn.R
import com.behnamuix.popcorn.view.components.FavoriteComp
import com.behnamuix.popcorn.view.components.HomeComp
import com.behnamuix.popcorn.view.components.SearchBarComp

object HomeTab:Tab{
    override val options: TabOptions
        @Composable
        get(){
            val title="خانه"
            val icon= painterResource(R.drawable.icon_home)
            return remember {
                TabOptions(
                    index = 0u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {
        HomeComp()

    }

}
object FavoriteTab:Tab{
    override val options: TabOptions
        @Composable
        get(){
            var title="منتخب"
            var icon=painterResource(R.drawable.icon_favorites)
            return remember {
                TabOptions(
                    index=1u,
                    title=title,
                    icon=icon
                )
            }
        }

    @Composable
    override fun Content() {
        FavoriteComp()
    }

}
object SearchTab: Tab{
    override val options: TabOptions
        @Composable
        get(){
            var title="جستجو"
            var icon=painterResource(R.drawable.icon_search)
            return remember {
                TabOptions(
                    index = 2u,
                    title=title,
                    icon = icon
                )
            }

        }

    @Composable
    override fun Content() {
        SearchBarComp()
    }

}

