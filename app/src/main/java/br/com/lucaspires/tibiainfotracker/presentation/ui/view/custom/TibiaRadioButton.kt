package br.com.lucaspires.tibiainfotracker.presentation.ui.view.custom

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import br.com.lucaspires.tibiainfotracker.R

internal class TibiaRadioButton(context: Context, attrs: AttributeSet) :
    androidx.appcompat.widget.AppCompatRadioButton(
        context,
        attrs,
        androidx.appcompat.R.attr.radioButtonStyle
    ) {

    init {
        buttonTintList = null
        buttonDrawable = ContextCompat.getDrawable(context, R.drawable.checkbox_status)
        typeface = ResourcesCompat.getFont(context, R.font.catamaran)
        setTextColor(ContextCompat.getColor(context, R.color.gray_2D3A40))
    }
}