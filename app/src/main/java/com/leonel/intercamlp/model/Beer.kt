package com.leonel.intercamlp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Beer (
    val id: Long,
    val name: String,
    val tagline: String,

    @SerializedName("first_brewed")
    val firstBrewed: String,

    val description: String,

    @SerializedName("image_url")
    val imageURL: String,

    val abv: Float,
    val ibu: Float,

    @SerializedName("target_fg")
    val targetFg: Float,

    @SerializedName("target_og")
    val targetOg: Float,

    val ebc: Float,
    val srm: Float,
    val ph: Float,

    @SerializedName("attenuation_level")
    val attenuationLevel: Float,

    val volume: BoilVolume,

    @SerializedName("boil_volume")
    val boilVolume: BoilVolume,

    val method: Method,
    val ingredients: Ingredients,

    @SerializedName("food_pairing")
    val foodPairing: List<String>,

    @SerializedName("brewers_tips")
    val brewersTips: String,

    @SerializedName("contributed_by")
    val contributedBy: String
) : Parcelable

@Parcelize
data class BoilVolume (
    val value: Float,
    val unit: String
) : Parcelable


enum class Unit(val value: String) {
    Celsius("celsius"),
    Grams("grams"),
    Kilograms("kilograms"),
    Litres("litres");

    companion object {
        public fun fromValue(value: String): Unit = when (value) {
            "celsius"   -> Celsius
            "grams"     -> Grams
            "kilograms" -> Kilograms
            "litres"    -> Litres
            else        -> throw IllegalArgumentException()
        }
    }
}

@Parcelize
data class Ingredients (
    val malt: List<Malt>,
    val hops: List<Hop>,
    val yeast: String
): Parcelable
@Parcelize
data class Hop (
    val name: String,
    val amount: BoilVolume,
    val add: String,
    val attribute: String
): Parcelable
@Parcelize
data class Malt (
    val name: String,
    val amount: BoilVolume
): Parcelable
@Parcelize
data class Method (
    @SerializedName("mash_temp")
    val mashTemp: List<MashTemp>,

    val fermentation: Fermentation,
    val twist: String? = null
): Parcelable
@Parcelize
data class Fermentation (
    val temp: BoilVolume
): Parcelable
@Parcelize
data class MashTemp (
    val temp: BoilVolume,
    val duration: Float? = null
): Parcelable