package br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom

import android.content.Context
import android.content.res.TypedArray
import android.text.Html
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.view.isVisible
import br.com.lucaspires.tibiainfotracker.R
import br.com.lucaspires.tibiainfotracker.databinding.ViewTibiaPairTextViewBinding
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.show

internal class TibiaPairTextView(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs, 0) {

    private var binding =
        ViewTibiaPairTextViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TibiaPairTextView, 0, 0)
        try {
            binding.textViewTibiaPairFirst.text = context.getString(
                R.string.add_two_dots,
                attributes.getText(R.styleable.TibiaPairTextView_firstText)
            )
            setupIcon(attributes)
        } finally {
            attributes.recycle()
        }
    }

    private fun setupIcon(attributes: TypedArray) {
        val icon = attributes.getDrawable(R.styleable.TibiaPairTextView_leftIcon)
        icon?.let {
            binding.imageViewPairTextView.background =
                attributes.getDrawable(R.styleable.TibiaPairTextView_leftIcon)
            binding.imageViewPairTextView.show()
        }
    }

    fun setMainText(second: String?) {
        isVisible = second.isNullOrEmpty().not()
        second?.let {
            binding.textViewTibiaPairSecond.text = Html.fromHtml(second)
        }
    }

    fun getMainText() = binding.textViewTibiaPairSecond.text.toString()
}