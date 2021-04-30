package br.com.lucaspires.tibiainfotracker.presentation.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.com.lucaspires.tibiainfotracker.AppConstants.GITHUB_LINK
import br.com.lucaspires.tibiainfotracker.AppConstants.LINKEDIN_LINK
import br.com.lucaspires.tibiainfotracker.databinding.ActivityAboutBinding
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.showAnimation
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.goToLink
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.hide
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.onObserver
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.show
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom.TibiaFeedback
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler.SupportAdapter
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.about.AboutViewModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.viewmodel.action.TibiaAction
import org.koin.android.ext.android.inject

internal class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    private val adapter by lazy { SupportAdapter() }

    private val viewModel: AboutViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupView()
        setupObserver()
        viewModel.getSuppSites()
    }

    private fun setupView() {
        with(binding) {
            recylerViewAboutSupp.adapter = adapter
            imageViewAboutAuthorGitHub.setOnClickListener { goToLink(GITHUB_LINK) }
            imageViewAboutAuthorLinkedin.setOnClickListener { goToLink(LINKEDIN_LINK) }
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupObserver() {
        onObserver(viewModel.observableStatus) {
            adapter.addItems(it)
            binding.recylerViewAboutSupp.show()
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

    private fun setFeedback(feedbackError: TibiaFeedback) {
        with(binding) {
            customFeedBack.show()
            recylerViewAboutSupp.hide()
            customFeedBack.setFeedback(feedbackError) {
                viewModel.getSuppSites()
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}