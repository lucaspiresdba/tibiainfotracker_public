package br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.lucaspires.tibiainfotracker.databinding.RecyclerViewItemHouseBinding
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.activity.HouseDetailsActivity

internal class HousesAdapter : BaseAdapter<HouseModel, HouseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HouseViewHolder {
        return HouseViewHolder(
            RecyclerViewItemHouseBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HouseViewHolder, position: Int) {
        val house = listOfItems[position]
        with(holder.binding) {
            textViewHouseName.setMainText(house.name)
            textViewHouseRent.setMainText(house.rent.toString())
            textViewHouseSize.setMainText(house.size.toString())
            textViewHouseStatus.setMainText(house.status)
            root.setOnClickListener {
                HouseDetailsActivity.startDetailsActivity(root.context, house.world, house.houseId)
            }
        }
    }
}