package com.example.pokeapp.model

// # # # # #
//  PokemonDetails.kt
// # # # # #

data class PokemonDetails(
    val abilities: List<Ability>,
    val base_experience: Int,
    val forms: List<Any>, // Can be further defined if needed
    val game_indices: List<GameIndex>, // Can be further defined if needed
    val height: Int,
    val held_items: List<Any>, // Can be further defined if needed
    val id: Int,
    val is_default: Boolean,
    val location_area_encounters: String,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val past_abilities: List<Any>, // Can be further defined if needed
    val past_types: List<Any>, // Can be further defined if needed
    val species: Species,
    val sprites: Sprites,
    val stats: List<PokemonStat>, // Can be further defined if needed
    val types: List<PokemonType>,
    val weight: Int
)

data class Ability(
    val ability: AbilityInfo,
    val is_hidden: Boolean,
    val slot: Int
)

data class AbilityInfo(
    val name: String,
    val url: String
)

data class GameIndex(
    val game_index: Int,
    val version: GameVersion
)

data class GameVersion(
    val name: String,
    val url: String
)

data class Move(
    val move: MoveInfo,
    val version_group_details: List<VersionGroupDetail> // Can be further defined if needed
)

data class MoveInfo(
    val name: String,
    val url: String
)

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethod,
    val version_group: MoveVersionGroup
)

data class MoveLearnMethod(
    val name: String,
    val url: String
)

data class MoveVersionGroup(
    val name: String,
    val url: String
)

data class Species(
    val name: String,
    val url: String
)

data class Sprites(
    val back_default: String,
    val back_female: String?,
    val back_shiny: String,
    val back_shiny_female: String?,
    val front_default: String,
    val front_female: String?,
    val front_shiny: String,
    val front_shiny_female: String?,
    val other: Other,
    val versions: Any // Can be further defined if needed
)

data class Other(
    val dream_world: DreamWorld,
    val home: Home,
    val official_artwork: OfficialArtwork,
    val showdon: Any // Can be further defined if needed
)

data class DreamWorld(
    val front_default: String,
    val front_female: String?
)

data class Home(
    val front_default: String,
    val front_female: String?,
    val front_shiny: String,
    val front_shiny_female: String?
)

data class OfficialArtwork(
    val front_default: String,
    val front_shiny: String
)

data class PokemonStat(
    val base_stat: Int,
    val effort: Int,
    val stat: StatInfo
)

data class StatInfo(
    val name: String,
    val url: String
)

data class PokemonType(
    val slot: Int,
    val type: TypeInfo
)

data class TypeInfo(
    val name: String,
    val url: String
)