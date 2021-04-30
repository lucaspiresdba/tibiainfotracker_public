package br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import br.com.lucaspires.tibiainfotracker.R
import br.com.lucaspires.tibiainfotracker.databinding.ViewTibiaFeedbackErrorBinding
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.hide
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.show

enum class TibiaFeedback {
    CONNECTION, ERROR, NOT_FOUND, NO_FAVORITE
}

internal class TibiaCustomFeedbackError(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs, 0) {

    private val binding =
        ViewTibiaFeedbackErrorBinding.inflate(LayoutInflater.from(context), this, true)

    fun setFeedback(feedback: TibiaFeedback, func: (() -> Any?)? = null) {
        val feedbackValues = when (feedback) {
            TibiaFeedback.CONNECTION -> {
                Pair(R.string.feedback_no_connection, R.drawable.ic_feedback_no_connection)
            }
            TibiaFeedback.ERROR -> {
                Pair(R.string.feedback_error, R.drawable.ic_feedback_error)
            }
            TibiaFeedback.NOT_FOUND -> {
                Pair(R.string.feedback_not_found, R.drawable.ic_feedback_not_found)
            }
            TibiaFeedback.NO_FAVORITE -> {
                with(binding.buttonAction) {
                    text = context.getString(R.string.feedback_button_add_favorite)
                    setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_favorite_selected_button,
                        0,
                        0,
                        0
                    )
                }
                Pair(R.string.feedback_no_favorites, R.drawable.ic_feedback_not_found)
            }
        }

        binding.textViewFeedback.text = context.getString(feedbackValues.first)
        binding.imageViewFeedback.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                feedbackValues.second
            )
        )

        func?.let {
            binding.buttonAction.let { button ->
                button.show()
                button.setOnClickListener {
                    func.invoke()
                    hide()
                }
            }
        }
    }
}


