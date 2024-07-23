@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.dvidal.rickandmortysample.ui.details

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun SharedTransitionScope.CharacterDetailsScreen(
    state: CharacterDetailsState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    val character = state.character

   Box(
       modifier = modifier
           .fillMaxSize()
           .padding(16.dp)
   ) {
       Column(
           modifier = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           AsyncImage(
               modifier = Modifier
                   .aspectRatio(16 / 9f)
                   .sharedElement(
                       state = rememberSharedContentState(key = "characterImage/${character.id}"),
                       animatedVisibilityScope = animatedVisibilityScope,
                       boundsTransform = { _, _ ->
                           tween(durationMillis = 1_000)
                       }
                   ),
               model = character.image,
               contentDescription = null
           )
           Spacer(modifier = Modifier.height(16.dp))
           Column(
               modifier = Modifier
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
                   style = MaterialTheme.typography.titleMedium
               )
               Spacer(modifier = Modifier.height(4.dp))
               Text(
                   text = character.status.ifBlank { "not defined" },
                   style = MaterialTheme.typography.labelMedium
               )
               Spacer(modifier = Modifier.height(4.dp))
               Text(
                   text = character.species.ifBlank { "not defined" },
                   style = MaterialTheme.typography.labelMedium
               )
               Spacer(modifier = Modifier.height(4.dp))
               Text(
                   text = character.type.ifBlank { "not defined" },
                   style = MaterialTheme.typography.labelMedium
               )
               Spacer(modifier = Modifier.height(4.dp))
               Text(
                   text = character.gender.ifBlank { "not defined" },
                   style = MaterialTheme.typography.labelMedium
               )
           }
       }
   }
}