package br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isGone
import br.com.lucaspires.tibiainfotracker.R
import br.com.lucaspires.tibiainfotracker.databinding.ViewTibiaTextViewBinding
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.show

internal class TibiaTextView(context: Context, attrs: AttributeSet) :
    FrameLayout(context, attrs, 0) {

    private var binding =
        ViewTibiaTextViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    init {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.TibiaTextView, 0, 0)
        try {
            setText(attributes.getString(R.styleable.TibiaTextView_text))
            setTypeface(attributes)
            setIcon(attributes)
            setTextSize(attributes)
            setTextColor(attributes)
            setIconSize(attributes)
        } finally {
            attributes.recycle()
        }
    }

    @SuppressLint("SetTextI18n")
    fun setText(text: String?) {
        isGone = text.isNullOrEmpty()
        binding.textViewText.text = text
    }

    private fun setIcon(attributes: TypedArray) {
        val icon = attributes.getDrawable(R.styleable.TibiaTextView_leftIconText)
        icon?.let {
            binding.imageViewTextView.background =
                attributes.getDrawable(R.styleable.TibiaTextView_leftIconText)
            binding.imageViewTextView.show()
        }
    }

    private fun setTypeface(attributes: TypedArray) {
        val typeface = attributes.getResourceId(R.styleable.TibiaTextView_setTypeface, 0)
        if (typeface > 0) {
            binding.textViewText.typeface = ResourcesCompat.getFont(context, typeface)
        }
    }

    private fun setTextSize(attributes: TypedArray) {
        val textSize =
            attributes.getDimensionPixelSize(R.styleable.TibiaTextView_setTextSize, 0).toFloat()
        if (textSize > 0) {
            binding.textViewText.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize)
        }
    }

    private fun setTextColor(attributes: TypedArray) {
        val color = attributes.getColor(
            R.styleable.TibiaTextView_setTextColor,
            ContextCompat.getColor(context, R.color.gray_2D3A40)
        )
        binding.textViewText.setTextColor(color)
    }

    private fun setIconSize(attributes: TypedArray) {
        val dimen = attributes.getDimensionPixelSize(R.styleable.TibiaTextView_setIconSize, 0)
        if (dimen > 0) {
            binding.imageViewTextView.layoutParams.run {
                width = dimen
                height = dimen
                requestLayout()
            }
        }
    }
}