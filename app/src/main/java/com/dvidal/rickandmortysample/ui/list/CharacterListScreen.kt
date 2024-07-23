@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.dvidal.rickandmortysample.ui.list

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dvidal.rickandmortysample.model.CharacterPreview

@Composable
fun SharedTransitionScope.CharacterListScreen(
    state: CharacterListState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit
) {
    Box(
        modifier = modifier
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.characters) { character ->
                CharacterListItem(character, animatedVisibilityScope, onItemClick)
            }
        }
    }
}

@Composable
fun SharedTransitionScope.CharacterListItem(
    character: CharacterPreview,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onItemClick: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .border(
                width = 1.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onItemClick.invoke(character.id) }
    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(shape = RoundedCornerShape(topStart = 4.dp, bottomStart = 4.dp))
                    .sharedElement(
                        state = rememberSharedContentState(key = "characterImage/${character.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1_000)
                        }
                    )
                ,
                model = character.image,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier
                    .padding( top = 12.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "characterText/${character.id}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 1_000)
                        }
                    )
            ) {
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = character.species,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}