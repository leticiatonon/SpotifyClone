package com.tonon.spotifycloneapp.model

import com.google.gson.annotations.SerializedName
import com.tonon.spotifycloneapp.R

data class Category(
        @SerializedName("titulo") var title: String = "",
        @SerializedName("albuns")var albuns: List<Album> = arrayListOf()
)

data class Album(
        @SerializedName("url_imagem") var album: String = "",
        @SerializedName("id") var id: Int = 0
)

data class Categories(@SerializedName("categoria")

        val categories: List<Category>
)

data class Section(

        var section: Int,
        var nameSection: String
)

class sectionBuilder{
        var section: Int = 0
        var nameSection: String = ""

        fun build(): Section = Section(section, nameSection)
}

fun section(block: sectionBuilder.() -> Unit): Section = sectionBuilder().apply(block).build()

fun data(): MutableList<Section> = mutableListOf(

        section {
                section = R.drawable.secao1
                nameSection = "Podcast"
        },
        section {
                section = R.drawable.secao2
                nameSection = "Made for you"
        },
        section {
                section = R.drawable.secao3
                nameSection = "Brasil"
        },
        section {
                section = R.drawable.secao4
                nameSection = "Rock"
        },
        section {
                section = R.drawable.secao1
                nameSection = "Lançamentos"
        },
        section {
                section = R.drawable.secao2
                nameSection = "Em casa"
        },
        section {
                section = R.drawable.secao3
                nameSection = "Paradas"
        },
        section {
                section = R.drawable.secao4
                nameSection = "Shows"
        }
)