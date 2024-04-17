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
import com.example.pokeapp.model.PokemonListItem
import com.example.pokeapp.model.PokemonListModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface PokemonUiState {
    data class Success(val pokemon: PokemonListModel) : PokemonUiState
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
                PokemonUiState.Success(pokemonRepository.getAllPokemon())
            } catch (e: IOException) {
                PokemonUiState.Error
            } catch (e: HttpException) {
                PokemonUiState.Error
            }
        }
    }
    // Same as in the PokemonRepository but accessible through view model
    fun convertPokemonList(pokemonList: List<PokemonListItem>, spriteMode: Boolean): List<Pokemon> {
        return pokemonList.map { pokemonListItem ->
            val id = extractIdFromUrl(pokemonListItem.url)
            val spriteUrl = if (spriteMode) {
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
            } else {
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
            }
            Pokemon(
                id = id,
                name = pokemonListItem.name,
                imageUrl = spriteUrl,
                infoUrl = pokemonListItem.url
            )
        }
    }

    // Same as in the PokemonRepository but accessible through view model
    fun extractIdFromUrl(url: String): Int {
        val segments = url.split("/")
        return segments[segments.lastIndex - 1].toInt()
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
    fun getRegionName(id: Int): String {
        return when (id) {
            in 1..151 -> "Kanto"
            in 152..251 -> "Johto"
            in 252..386 -> "Hoenn"
            in 387..493 -> "Sinnoh"
            in 494..649 -> "Unova"
            in 650..721 -> "Kalos"
            in 722..809 -> "Alola"
            in 810..905 -> "Galar"
            in 906..1026 -> "Paldea"
            else -> "Unknown Region"
        }
    }

    // Example usage
    // val id = 1
    // val regionName = getRegionName(id)
}
