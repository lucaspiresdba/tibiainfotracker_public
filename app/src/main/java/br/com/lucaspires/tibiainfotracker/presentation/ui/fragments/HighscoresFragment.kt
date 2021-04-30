package br.com.lucaspires.tibiainfotracker.presentation.ui.fragments

import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import br.com.lucaspires.tibiainfotracker.R
import br.com.lucaspires.tibiainfotracker.databinding.FragmentHighscoreBinding
import br.com.lucaspires.tibiainfotracker.presentation.ui.dialog.OnItemSelected
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.*
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom.TibiaFeedback
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler.HighscoresAdapter
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.highscore.HighscoreViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class HighscoresFragment : Fragment() {

    private lateinit var binding: FragmentHighscoreBinding
    private val adapter by lazy { HighscoresAdapter() }

    private val viewModel: HighscoreViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHighscoreBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        setupObserver()
        viewModel.getWorlds()
    }

    private fun setupObserver() {
        setToolbarTitle(R.string.toolbar_title_highscores)
        with(binding) {
            onObserver(viewModel.observableStatus) {
                it.highscores?.rankList?.let { list ->
                    adapter.addItems(list)
                }

                recyclerViewHighscores.show()
                feedbackHighscore.hide()
                it.worldsList?.let { it1 -> setupWorlds(it1) }
            }

            onObserver(viewModel.observableLoading) {
                animationView.showAnimation(it)
            }

            onObserver(viewModel.observableAction) {
                when (it) {
                    TibiaAction.GenericError -> setFeedback(TibiaFeedback.ERROR)
                    TibiaAction.NoInternet -> setFeedback(TibiaFeedback.CONNECTION)
                    TibiaAction.NotFoundData -> setFeedback(TibiaFeedback.NOT_FOUND)
                }
            }
        }
    }

    private fun setupWorlds(worlds: List<String>) {
        if (worlds.isNotEmpty()) {
            binding.textViewHighscoreWorldSelector.setOnClickListener {
                showItemSelectorDialog(
                    list = worlds,
                    callback = object : OnItemSelected {
                        override fun setItem(item: String) {
                            binding.textViewHighscoreWorldSelector.text = item
                        }
                    },
                    title = R.string.choose_world
                )
            }
        }
    }

    private fun setupView() {
        with(binding) {
            recyclerViewHighscores.adapter = adapter
            imageButtonArrow.setOnClickListener {
                setRotationAnim(it)
            }
            buttonSearch.setOnClickListener {
                imageButtonArrow.performClick()
                recyclerViewHighscores.hide()
                feedbackHighscore.hide()
                viewModel.getHighscores(
                    textViewHighscoreWorldSelector.text.toString(),
                    getRadioButtonValueById(radioGroupFilter.checkedRadioButtonId),
                    getRadioButtonValueById(radioGroupVocation.checkedRadioButtonId)
                )
            }
        }
    }

    //Todo melhorar animações
    private fun toggle(show: Boolean) {
        val targetView = binding.filterContainerData
        val transition = Slide(Gravity.TOP)
        if (!show) targetView.isVisible = show
        transition.duration = ANIM_DURATION
        transition.addTarget(binding.filterContainerData)
        TransitionManager.beginDelayedTransition(binding.filterContainer, transition)
        if (show) targetView.isVisible = show
    }

    private fun setRotationAnim(view: View) {
        val toAngle = if (view.rotation == ANGLE_180) {
            toggle(true)
            ANGLE_0
        } else {
            toggle(false)
            ANGLE_180
        }
        view.animate()
            .rotation(toAngle)
            .setDuration(ANIM_DURATION)
            .interpolator = LinearInterpolator()
    }

    private fun getRadioButtonValueById(rb: Int): String {
        return when (rb) {
            R.id.radioButtonExp -> EXP
            R.id.radioButtonMagic -> MAGIC
            R.id.radioButtonShield -> SHIELD
            R.id.radioButtonDist -> DIST
            R.id.radioButtonSword -> SWORD
            R.id.radioButtonAxe -> AXE
            R.id.radioButtonClub -> CLUB
            R.id.radioButtonFist -> FIST
            R.id.radioButtonFishing -> FISHING
            R.id.radioButtonAchi -> ACHI
            R.id.radioButtonGoshnar -> GOSHNAR
            R.id.radioButtonCharm -> CHARM
            R.id.radioButtonLoyalt -> LOYALTY
            R.id.radioButtonPaladin -> PALADIN
            R.id.radioButtonKnight -> KNIGHT
            R.id.radioButtonDruid -> DRUID
            R.id.radioButtonSorcerer -> SORCERER
            else -> ALL
        }
    }

    private fun setFeedback(feedbackError: TibiaFeedback) {
        with(binding) {
            feedbackHighscore.show()
            feedbackHighscore.setFeedback(feedbackError) { imageButtonArrow.performClick() }
        }
    }
}

private const val EXP = "experience"
private const val MAGIC = "magic"
private const val SHIELD = "shielding"
private const val DIST = "distance"
private const val SWORD = "sword"
private const val AXE = "axe"
private const val CLUB = "club"
private const val FIST = "fist"
private const val FISHING = "fishing"
private const val ACHI = "achievements"
private const val GOSHNAR = "goshnarstaint"
private const val CHARM = "charmpoints"
private const val LOYALTY = "loyalty"
private const val PALADIN = "paladin"
private const val KNIGHT = "knight"
private const val DRUID = "druid"
private const val SORCERER = "sorcerer"
private const val ALL = "all"
private const val ANGLE_180 = 180f
private const val ANGLE_0 = 0f
private const val ANIM_DURATION = 200L