package br.com.lucaspires.tibiainfotracker.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.lucaspires.tibiainfotracker.R
import br.com.lucaspires.tibiainfotracker.databinding.FragmentHousesBinding
import br.com.lucaspires.tibiainfotracker.presentation.ui.dialog.OnItemSelected
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.*
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom.TibiaFeedback
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler.HousesAdapter
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.houses.HousesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HousesFragment : Fragment() {

    private lateinit var binding: FragmentHousesBinding
    private val viewModel: HousesViewModel by viewModel()
    private val adapter by lazy { HousesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHousesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserver()
        getWorlds()
    }

    private fun setupView() {
        setToolbarTitle(R.string.toolbar_title_houses)
        with(binding) {
            recyclerViewHouses.adapter = adapter
            radioButtonHouse.setOnClickListener { getHouses() }
            radioButtonGuild.setOnClickListener { getHouses() }
        }
    }

    private fun setupObserver() {
        onObserver(viewModel.observableStatus) { status ->
            adapter.addItems(status.houses)
            setupWorlds(status.worlds)
            setupCities(status.cities)
        }

        onObserver(viewModel.observableAction) { action ->
            when (action) {
                TibiaAction.NoInternet -> setFeedback(TibiaFeedback.CONNECTION)
                else -> setFeedback(TibiaFeedback.ERROR)
            }
        }

        onObserver(viewModel.observableLoading) {
            binding.animationView.showAnimation(it)
        }
    }

    private fun setupWorlds(worlds: List<String>) {
        with(binding) {
            if (worlds.isNotEmpty()) {
                textViewWorldSelector.setOnClickListener {
                    showItemSelectorDialog(
                        list = worlds,
                        callback = object : OnItemSelected {
                            override fun setItem(item: String) {
                                textViewWorldSelector.text = item
                                onDataChanged()
                            }
                        },
                        title = R.string.choose_world
                    )
                }
                containerHouses.show()
                feedbackHouse.hide()
            }
        }
    }

    private fun setupCities(cities: List<String>) {
        if (cities.isNotEmpty()) {
            binding.textViewCitySelector.setOnClickListener {
                showItemSelectorDialog(
                    list = cities,
                    callback = object : OnItemSelected {
                        override fun setItem(item: String) {
                            binding.textViewCitySelector.text = item
                            onDataChanged()
                        }
                    },
                    title = R.string.choose_town
                )
            }
        }
    }

    private fun setFeedback(feedbackError: TibiaFeedback) {
        with(binding) {
            feedbackHouse.show()
            containerHouses.hide()
            feedbackHouse.setFeedback(feedbackError) {
                if (radionButtonGroup.checkedRadioButtonId != -1) {
                    getHouses()
                }
            }
        }
    }

    private fun getWorlds() {
        with(binding) {
            feedbackHouse.hide()
            containerHouses.hide()
            viewModel.getWorldsAndCities()
        }
    }

    private fun getHouses() {
        with(binding) {
            if (radioButtonGuild.isChecked ||
                radioButtonHouse.isChecked
            ) {
                containerHouses.hide()
                recyclerViewHouses.show()
                viewModel.getHouses(
                    world = textViewWorldSelector.text.toString(),
                    town = textViewCitySelector.text.toString(),
                    isGuildHouse = radioButtonGuild.isChecked
                )
            }
        }
    }

    private fun onDataChanged() {
        with(binding) {
            recyclerViewHouses.hide()
            radionButtonGroup.clearCheck()
        }
    }
}