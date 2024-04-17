package com.example.pokeapp.ui.screens
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import com.example.pokeapp.model.PokemonDetails
import com.example.pokeapp.model.PokemonListItem
import com.example.pokeapp.model.PokemonListModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface PokemonUiState { // Sealed state interface
    data class Success(
        val pokemon: PokemonListModel = PokemonListModel() // Stores the list of basic pokemon info
    ) : PokemonUiState
    object Error : PokemonUiState
    object Loading : PokemonUiState
}
class PokemonViewModel( // View model to store data
    private val pokemonRepository: PokemonRepository // Create a repository instance to use data pull functions
) : ViewModel() {
    var pokemonUiState: PokemonUiState by mutableStateOf(PokemonUiState.Loading) // Create an instance of the ui state to store in view model
        private set
    init {
        getPokemon() // Grab list of pokemon when ui state is initialized
    }
    private val _selectedPokemonId = mutableIntStateOf(-1) // Store the id of the currently selected pokemon
    val selectedPokemonId: MutableIntState = _selectedPokemonId

    fun setSelectedPokemonId(id: Int) { // Function to set the id
        _selectedPokemonId.intValue = id
    }

    private val _selectedPokemonDetails = MutableStateFlow<PokemonDetails>(PokemonDetails()) // Store the currently selected pokemon's details
    val selectedPokemonDetails: StateFlow<PokemonDetails> = _selectedPokemonDetails

    fun getPokemonDetails(pokemonId: Int) { // Update details object based on passed in id
        viewModelScope.launch {
            val details = pokemonRepository.getPokemonDetails(pokemonId) //Create new pokemon details object with info pulled from api
            _selectedPokemonDetails.value = details
        }
    }
    fun getPokemon() { // Grab the pokemon
        viewModelScope.launch {
            pokemonUiState = PokemonUiState.Loading
            pokemonUiState = try {
                PokemonUiState.Success(pokemon = pokemonRepository.getAllPokemon())
            } catch (e: IOException) {
                PokemonUiState.Error
            } catch (e: HttpException) {
                PokemonUiState.Error
            }
        }
    }

    // Same as in the PokemonRepository but accessible through view model
    fun convertPokemonList(pokemonList: List<PokemonListItem>): List<Pokemon> {
        return pokemonList.map { pokemonListItem ->
            val id = extractIdFromUrl(pokemonListItem.url)
            Pokemon(
                id = id,
                name = pokemonListItem.name,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png",
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
}