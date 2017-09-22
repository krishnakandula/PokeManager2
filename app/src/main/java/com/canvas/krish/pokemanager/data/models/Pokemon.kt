package com.canvas.krish.pokemanager.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import com.google.gson.annotations.SerializedName


/**
 * Created by Krishna Chaitanya Kandula on 9/18/2017.
 */
@Parcelize
data class Pokemon(
        @SerializedName("id") val id: Int, //12
        @SerializedName("name") val name: String, //butterfree
        @SerializedName("base_experience") val baseExperience: Int, //178
        @SerializedName("height") val height: Int, //11
        @SerializedName("is_default") val isDefault: Boolean, //true
        @SerializedName("order") val order: Int, //16
        @SerializedName("weight") val weight: Int, //320
        @SerializedName("abilities") val abilities: List<Abilities>,
        @SerializedName("forms") val forms: List<Form>,
        @SerializedName("game_indices") val gameIndices: List<GameIndice>,
        @SerializedName("held_items") val heldItems: List<HeldItem>,
        @SerializedName("location_area_encounters") val locationAreaEncounters: String,
        @SerializedName("moves") val moves: List<Moves>,
        @SerializedName("species") val species: Species,
        @SerializedName("sprites") val sprites: Sprites,
        @SerializedName("stats") val stats: List<Stats>,
        @SerializedName("types") val types: List<Types>
) : Parcelable

@Parcelize
data class Abilities(
        @SerializedName("is_hidden") val isHidden: Boolean, //true
        @SerializedName("slot") val slot: Int, //3
        @SerializedName("ability") val ability: Ability
) : Parcelable

@Parcelize
data class Ability(
        @SerializedName("name") val name: String, //tinted-lens
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/ability/110/
) : Parcelable

@Parcelize
data class Species(
        @SerializedName("name") val name: String, //butterfree
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/pokemon-species/12/
) : Parcelable

@Parcelize
data class Stats(
        @SerializedName("base_stat") val baseStat: Int, //70
        @SerializedName("effort") val effort: Int, //0
        @SerializedName("stat") val stat: Stat
) : Parcelable

@Parcelize
data class Stat(
        @SerializedName("name") val name: String, //speed
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/stat/6/
) : Parcelable

@Parcelize
data class Moves(
        @SerializedName("move") val move: Move,
        @SerializedName("version_group_details") val versionGroupDetails: List<VersionGroupDetail>
) : Parcelable

@Parcelize
data class VersionGroupDetail(
        @SerializedName("level_learned_at") val levelLearnedAt: Int, //0
        @SerializedName("version_group") val versionGroup: VersionGroup,
        @SerializedName("move_learn_method") val moveLearnMethod: MoveLearnMethod
) : Parcelable

@Parcelize
data class VersionGroup(
        @SerializedName("name") val name: String, //x-y
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/version-group/15/
) : Parcelable

@Parcelize
data class MoveLearnMethod(
        @SerializedName("name") val name: String, //machine
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/move-learn-method/4/
) : Parcelable

@Parcelize
data class Move(
        @SerializedName("name") val name: String, //flash
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/move/148/
) : Parcelable

@Parcelize
data class Types(
        @SerializedName("slot") val slot: Int, //2
        @SerializedName("type") val type: PokemonType
) : Parcelable

@Parcelize
data class PokemonType(
        @SerializedName("name") val name: String, //flying
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/type/3/
) : Parcelable

@Parcelize
data class Form(
        @SerializedName("name") val name: String, //butterfree
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/pokemon-form/12/
) : Parcelable

@Parcelize
data class LocationAreaEncounter(
        @SerializedName("location_area") val locationArea: LocationArea,
        @SerializedName("version_details") val versionDetails: List<LocationAreaEncounterVersionDetail>
) : Parcelable

@Parcelize
data class LocationArea(
        @SerializedName("name") val name: String, //kanto-route-2-south-towards-viridian-city
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/location-area/296/
) : Parcelable

@Parcelize
data class LocationAreaEncounterVersionDetail(
        @SerializedName("max_chance") val maxChance: Int, //10
        @SerializedName("encounter_details") val encounterDetails: List<EncounterDetail>,
        @SerializedName("version") val version: LocationAreaEncounterVersion
) : Parcelable

@Parcelize
data class LocationAreaEncounterVersion(
        @SerializedName("name") val name: String, //heartgold
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/version/15/
) : Parcelable

@Parcelize
data class EncounterDetail(
        @SerializedName("min_level") val minLevel: Int, //7
        @SerializedName("max_level") val maxLevel: Int, //7
        @SerializedName("condition_values") val conditionValues: List<ConditionValue>,
        @SerializedName("chance") val chance: Int, //5
        @SerializedName("method") val method: Method
) : Parcelable

@Parcelize
data class ConditionValue(
        @SerializedName("name") val name: String, //time-morning
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/encounter-condition-value/3/
) : Parcelable

@Parcelize
data class Method(
        @SerializedName("name") val name: String, //walk
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/encounter-method/1/
) : Parcelable

@Parcelize
data class HeldItem(
        @SerializedName("item") val item: Item,
        @SerializedName("version_details") val versionDetails: List<VersionDetail>
) : Parcelable

@Parcelize
data class Item(
        @SerializedName("name") val name: String, //silver-powder
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/item/199/
) : Parcelable

@Parcelize
data class VersionDetail(
        @SerializedName("rarity") val rarity: Int, //5
        @SerializedName("version") val version: Version
) : Parcelable

@Parcelize
data class Version(
        @SerializedName("name") val name: String, //y
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/version/24/
) : Parcelable

@Parcelize
data class GameIndice(
        @SerializedName("game_index") val gameIndex: Int, //12
        @SerializedName("version") val version: GameIndiceVersion
) : Parcelable

@Parcelize
data class GameIndiceVersion(
        @SerializedName("name") val name: String, //white-2
        @SerializedName("url") val url: String //http://pokeapi.co/api/v2/version/22/
) : Parcelable

@Parcelize
data class Sprites(
        @SerializedName("back_female") val backFemale: String, //http://pokeapi.co/media/sprites/pokemon/back/female/12.png
        @SerializedName("back_shiny_female") val backShinyFemale: String, //http://pokeapi.co/media/sprites/pokemon/back/shiny/female/12.png
        @SerializedName("back_default") val backDefault: String, //http://pokeapi.co/media/sprites/pokemon/back/12.png
        @SerializedName("front_female") val frontFemale: String, //http://pokeapi.co/media/sprites/pokemon/female/12.png
        @SerializedName("front_shiny_female") val frontShinyFemale: String, //http://pokeapi.co/media/sprites/pokemon/shiny/female/12.png
        @SerializedName("back_shiny") val backShiny: String, //http://pokeapi.co/media/sprites/pokemon/back/shiny/12.png
        @SerializedName("front_default") val frontDefault: String, //http://pokeapi.co/media/sprites/pokemon/12.png
        @SerializedName("front_shiny") val frontShiny: String //http://pokeapi.co/media/sprites/pokemon/shiny/12.png
) : Parcelable
