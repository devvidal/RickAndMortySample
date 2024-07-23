@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.dvidal.rickandmortysample.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.dvidal.rickandmortysample.domain.usecase.FetchCharacters
import com.dvidal.rickandmortysample.navigation.Route
import com.dvidal.rickandmortysample.ui.details.CharacterDetailsScreen
import com.dvidal.rickandmortysample.ui.details.CharacterDetailsViewModel
import com.dvidal.rickandmortysample.ui.list.CharacterListScreen
import com.dvidal.rickandmortysample.ui.list.CharacterListViewModel
import com.dvidal.rickandmortysample.ui.theme.RickAndMortySampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RickAndMortySampleTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    SharedTransitionLayout {

                        NavHost(
                            navController = navController,
                            startDestination = Route.List
                        ) {
                            composable<Route.List> {
                                val viewModel: CharacterListViewModel = hiltViewModel()
                                CharacterListScreen(
                                    state = viewModel.state,
                                    animatedVisibilityScope = this,
                                    modifier = Modifier.padding(innerPadding),
                                    onItemClick = { characterId ->
                                        navController.navigate(
                                            route = Route.Details(characterId)
                                        )
                                    }
                                )
                            }
                            composable<Route.Details> {
                                val viewModel: CharacterDetailsViewModel = hiltViewModel()
                                val characterId = it.toRoute<Route.Details>().characterId

                                LaunchedEffect(key1 = true) {
                                    viewModel.invokeAction(characterId)
                                }

                                CharacterDetailsScreen(
                                    state = viewModel.state,
                                    animatedVisibilityScope = this,
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}