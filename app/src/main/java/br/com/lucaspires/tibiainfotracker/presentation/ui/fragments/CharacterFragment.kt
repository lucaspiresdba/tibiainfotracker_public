package br.com.lucaspires.tibiainfotracker.presentation.ui.fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import br.com.lucaspires.tibiainfotracker.R
import br.com.lucaspires.tibiainfotracker.databinding.FragmentCharacterInfoBinding
import br.com.lucaspires.tibiainfotracker.domain.model.character.*
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.*
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom.TibiaFeedback
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler.DeathsAdapter
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction.*
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.character.CharacterViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


internal class CharacterFragment : Fragment() {

    private lateinit var binding: FragmentCharacterInfoBinding
    private val adapter by lazy { DeathsAdapter() }

    private val viewModel: CharacterViewModel by viewModel()

    private val args: CharacterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObservables()
    }

    private fun setupView() {
        setToolbarTitle(R.string.toolbar_title_characters)
        with(binding) {
            serchView.onClickSearch {
                viewModel.getCharacter(it)
                hideViews()
                hideKeybaord()
            }
            recyclerViewDeaths.adapter = adapter
            args.characterName?.let {
                serchView.searchValue(it)
            }
        }
    }

    private fun setupObservables() {
        onObserver(viewModel.observableAction) {
            when (it) {
                GenericError -> setFeedback(TibiaFeedback.ERROR)
                NoInternet -> setFeedback(TibiaFeedback.CONNECTION)
                NotFoundData -> setFeedback(TibiaFeedback.NOT_FOUND)
            }
        }

        onObserver(viewModel.observableStatus) {
            configureScreen(it)
        }

        onObserver(viewModel.observableLoading) {
            configureLoading(it)
        }
    }

    private fun configureLoading(loading: Boolean) {
        binding.animationView.showAnimation(loading)
    }

    private fun configureScreen(data: CharacterInfoModel) {
        with(binding) {
            textViewLevelLabel.setText(data.level.toString())
            textViewNameLabel.setMainText(data.name)
            textViewMarried.setMainText(data.married)
            textViewResidenceLabel.setMainText(data.residence)
            textViewWorldLabel.setMainText(data.world)
            textViewOldNameLabel.setMainText(data.oldName)
            textViewOldWorldLabel.setMainText(data.oldWorld)
            characterInfoContainer.show()
            textViewMarried.setOnClickListener {
                serchView.searchValue(textViewMarried.getMainText())
            }
            data.name?.let { setupFavorite(data.isFavorite, it) }
        }

        setupGuild(data.guild)
        setupHouse(data.house)
        setupDeaths(data.deaths)
        setupSexAndVocation(data.sex, data.vocation)
    }

    private fun setupSexAndVocation(sex: SexEnum?, vocation: VocationEnum?) {
        sex?.let {
            val sexIcon = when (sex) {
                SexEnum.F -> R.drawable.ic_female
                SexEnum.M -> R.drawable.ic_male
            }
            binding.imageViewSex.setImageResource(sexIcon)
        }

        vocation?.let {
            val vocationIcon = when (vocation) {
                VocationEnum.EK -> R.drawable.ic_knight
                VocationEnum.RP -> R.drawable.ic_paladin
                VocationEnum.ED -> R.drawable.ic_druid
                VocationEnum.MS -> R.drawable.ic_sorcerer
            }
            binding.imageViewVocation.setImageResource(vocationIcon)
        }
    }

    private fun setupGuild(guild: GuildInfoModel?) {
        with(binding) {
            val hasInfo = !guild?.guildName.isNullOrEmpty()
            cardGuildContainer.isVisible = hasInfo
            if (hasInfo) {
                textViewGuildName.setMainText(guild?.guildName)
                textViewGuildRank.setMainText(guild?.guildRank)
            }
        }
    }

    private fun setupHouse(house: HouseInfoModel?) {
        with(binding) {
            val hasInfo = !house?.houseName.isNullOrEmpty()
            cardHouseContainer.isVisible = hasInfo
            if (hasInfo) {
                textViewHouseName.setMainText(house?.houseName)
                textViewHouseTownLabel.setMainText(house?.houseTown)
                cardHouseContainer.show()
            }
        }
    }

    private fun setupDeaths(deaths: List<DeathModel>?) {
        deaths?.isNotEmpty()?.let { binding.cardDeathContainer.isVisible = it }
        deaths?.let { adapter.addItems(it) }
    }

    private fun setFeedback(feedbackError: TibiaFeedback) {
        with(binding) {
            customFeedBack.show()
            customFeedBack.setFeedback(feedbackError)
        }
    }

    private fun hideViews() {
        with(binding) {
            customFeedBack.hide()
            characterInfoContainer.hide()
        }
    }

    private fun setupFavorite(favorite: Boolean, name: String) {
        with(binding.imageViewFavorite) {
            setImageResource(if (favorite) R.drawable.ic_favorite_selected else R.drawable.ic_favorite_unselected)
            setOnClickListener { viewModel.setFavorite(name) }
        }
    }

    private fun hideKeybaord() {
        val view = activity?.currentFocus
        val inputMethodManager: InputMethodManager? =
            activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(view?.applicationWindowToken, 0)
    }
}