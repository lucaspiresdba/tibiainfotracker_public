package br.com.lucaspires.tibiainfotracker.domain.model.houses

data class HouseDetailModel(
    val name: String?,
    val owner: String?,
    val beds: Int?,
    val rent: Int?,
    val size: Int?,
    val imageUrl: String?
)