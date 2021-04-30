package br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.widget.doOnTextChanged
import br.com.lucaspires.tibiainfotracker.databinding.ViewTibiaSearchBinding

private const val MINIMUN_SEARCH_LENGHT_STRING = 1

internal class TibiaSearchView(context: Context, attr: AttributeSet) :
    ConstraintLayout(context, attr, 0) {

    private var binding =
        ViewTibiaSearchBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        configureView()
    }

    private fun configureView() {
        binding.buttonSearchChar.isEnabled = false
        binding.editTextNameChar.doOnTextChanged { text, _, _, _ ->
            binding.buttonSearchChar.isEnabled =
                text?.let { it.length >= MINIMUN_SEARCH_LENGHT_STRING } ?: false
        }
    }

    fun onClickSearch(function: (String) -> Unit) {
        binding.buttonSearchChar.setOnClickListener {
            function.invoke(binding.editTextNameChar.text.toString())
        }
    }

    fun searchValue(text: String) {
        binding.editTextNameChar.setText(text)
        binding.buttonSearchChar.performClick()
    }
}
