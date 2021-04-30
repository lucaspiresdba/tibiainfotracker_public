package br.com.lucaspires.tibiainfotracker.domain.model.support

import androidx.annotation.Keep

@Keep
data class SupportModel(
    val name: String? = null,
    val imgUrl: String? = null,
    val url: String? = null
)
