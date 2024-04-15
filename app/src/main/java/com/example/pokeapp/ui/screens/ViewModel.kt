package com.example.pokeapp.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.pokeapp.PokeAppApplication
import com.example.pokeapp.data.PokemonRepository
import com.example.pokeapp.model.Pokemon
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


/* Main Screen UI State */
sealed interface PokemonUiState {
    data class Success(val pokemon: List<Pokemon>) : PokemonUiState
    object Error : PokemonUiState
    object Loading : PokemonUiState
}

class PokemonViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    var pokemonUiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading)
        private set
    init {
        getPokemon()
    }

    fun getPokemon() {
        viewModelScope.launch {
            pokemonUiState = PokemonUiState.Loading
            pokemonUiState = try {
                PokemonUiState.Success(pokemonRepository.fetchAllPokemon())
            } catch (e: IOException) {
                PokemonUiState.Error
            } catch (e: HttpException) {
                PokemonUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as PokeAppApplication)
                val pokemonRepository = application.container.pokemonRepository
                PokemonViewModel(pokemonRepository = pokemonRepository)
            }
        }
    }
}