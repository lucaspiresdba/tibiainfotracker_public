package br.com.lucaspires.tibiainfotracker.presentation.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.lucaspires.tibiainfotracker.databinding.ActivityHouseDetailsBinding
import br.com.lucaspires.tibiainfotracker.domain.model.houses.HouseDetailModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.loadImage
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.onObserver
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.show
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.showAnimation
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom.TibiaFeedback
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.houses.HouseDetailViewModel
import org.koin.android.ext.android.inject

internal class HouseDetailsActivity : AppCompatActivity() {

    companion object {
        private const val WORLD_BUNDLE = "WORLD_BUNDLE"
        private const val HOUSE_ID_BUNDLE = "HOUSE_ID_BUNDLE"
        fun startDetailsActivity(context: Context, world: String, houseId: Int) {
            context.startActivity(
                Intent(context, HouseDetailsActivity::class.java)
                    .apply {
                        putExtra(WORLD_BUNDLE, world)
                        putExtra(HOUSE_ID_BUNDLE, houseId)
                    })
        }
    }

    private lateinit var binding: ActivityHouseDetailsBinding

    private val viewModel: HouseDetailViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHouseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupObserver()
        getIntentData()
    }

    private fun setupView() {
        binding.imageViewCloseDetails.setOnClickListener { finish() }
    }

    private fun getIntentData() {
        viewModel.getHouseDetails(
            world = intent.getStringExtra(WORLD_BUNDLE).orEmpty(),
            houseId = intent.getIntExtra(HOUSE_ID_BUNDLE, 0)
        )
    }

    private fun setupObserver() {
        onObserver(viewModel.observableStatus) {
            configureScreen(it)
        }

        onObserver(viewModel.observableAction) {
            when (it) {
                TibiaAction.GenericError -> setFeedback(TibiaFeedback.ERROR)
                TibiaAction.NoInternet -> setFeedback(TibiaFeedback.CONNECTION)
                TibiaAction.NotFoundData -> setFeedback(TibiaFeedback.NOT_FOUND)
            }
        }

        onObserver(viewModel.observableLoading) {
            binding.animationView.showAnimation(it)
        }
    }

    private fun configureScreen(details: HouseDetailModel?) {
        with(binding) {
            textViewHouseDetailName.setMainText(details?.name)
            textViewHouseDetailBeds.setMainText(details?.beds.toString())
            textViewHouseDetailOwner.setMainText(details?.owner)
            textViewHouseDetailRent.setMainText(details?.rent.toString())
            textViewHouseDetailSize.setMainText(details?.size.toString())
            imageViewHouseMap.loadImage(details?.imageUrl)
            houseDetailsContainer.show()
        }
    }

    private fun setFeedback(feedbackError: TibiaFeedback) {
        with(binding) {
            customFeedBack.show()
            customFeedBack.setFeedback(feedbackError) { getIntentData() }
        }
    }

}