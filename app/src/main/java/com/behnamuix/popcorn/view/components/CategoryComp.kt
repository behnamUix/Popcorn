package com.behnamuix.popcorn.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SelectableChipColors
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.behnamuix.popcorn.view.viewmodels.GenreViewModel
import org.koin.androidx.compose.koinViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.behnamuix.popcorn.utils.convertEngToPerGenre
import com.behnamuix.popcorn.view.navigation.voyager.MovieByGenreComp

import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryComp() {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = { true }
    )
    var selectedGenreId by remember { mutableStateOf<Int?>(null) }
    val genreViewModel: GenreViewModel = koinViewModel()
    val genres by genreViewModel.genres.collectAsState()
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "دسته بندی",
            style = MaterialTheme.typography.bodyLarge
        )
        LazyRow() {
            items(items = genres) {
                var selected by rememberSaveable { mutableStateOf(false) }
                FilterChip(
                    colors = FilterChipDefaults.filterChipColors(
                        selectedLabelColor = MaterialTheme.colorScheme.primary,
                        selectedLeadingIconColor = MaterialTheme.colorScheme.primary
                    ),
                    modifier = Modifier.padding(8.dp),
                    onClick = {
                        selectedGenreId = it.id
                        scope.launch {
                            sheetState.show()
                        }
                    },
                    label = {
                        Text(text = convertEngToPerGenre(ids = listOf(it.id)))
                    },

                    selected = selected,
                    leadingIcon = if (selected) {
                        {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = "Done icon",
                                modifier = Modifier.size(FilterChipDefaults.IconSize)
                            )
                        }
                    } else {
                        null
                    },
                )



            }
        }
        //baraye inke chandbar btn sheet baz nashe!
        if (selectedGenreId != null) {
            ModalBottomSheet(

                onDismissRequest = { selectedGenreId = null },
                sheetState = sheetState
            ) {
                MovieByGenreComp(selectedGenreId!!)
            }
        }
    }

}