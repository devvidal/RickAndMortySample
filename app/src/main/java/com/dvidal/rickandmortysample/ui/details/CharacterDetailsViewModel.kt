package com.dvidal.rickandmortysample.ui.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.viewModelScope
import com.dvidal.rickandmortysample.domain.usecase.FetchCharacter
import com.dvidal.rickandmortysample.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailsViewModel @Inject constructor(
    private val fetchCharacter: FetchCharacter
): ViewModel() {

    var state by mutableStateOf(CharacterDetailsState(Character()))
        private set

    fun invokeAction(id: Int) {
        handleFetchCharacter(id)
    }

    private fun handleFetchCharacter(id: Int) = viewModelScope.launch {
        val response = fetchCharacter.invoke(id)
        state = state.copy(character = response)
    }
}