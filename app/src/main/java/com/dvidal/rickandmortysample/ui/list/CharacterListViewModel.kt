package com.dvidal.rickandmortysample.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dvidal.rickandmortysample.domain.usecase.FetchCharacters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val fetchCharacters: FetchCharacters
): ViewModel() {

    var state by mutableStateOf(CharacterListState(emptyList()))
        private set

    init {
        loadCharacterList()
    }

    private fun loadCharacterList() = viewModelScope.launch {
        fetchCharacters.invoke(page = 1).onEach { response ->
            state = state.copy(characters = response)
        }.launchIn(viewModelScope)
    }
}