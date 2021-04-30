package br.com.lucaspires.tibiainfotracker.domain.model.houses

data class HouseModel(
    val name: String,
    val size: Int = 0,
    val rent: Int = 0,
    val status: String,
    val houseId: Int = 0,
    val world: String
)