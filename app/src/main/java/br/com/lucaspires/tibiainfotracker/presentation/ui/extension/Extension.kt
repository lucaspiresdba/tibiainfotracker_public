package br.com.lucaspires.tibiainfotracker.presentation.ui.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import br.com.lucaspires.tibiainfotracker.R
import br.com.lucaspires.tibiainfotracker.presentation.ui.activity.MainActivity
import br.com.lucaspires.tibiainfotracker.presentation.ui.dialog.ItemSelectorDialog
import br.com.lucaspires.tibiainfotracker.presentation.ui.dialog.OnItemSelected
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

internal fun <T> Fragment.onObserver(liveData: LiveData<T>, observer: Observer<T>) =
    liveData.observe(viewLifecycleOwner, observer)

internal fun <T> AppCompatActivity.onObserver(liveData: LiveData<T>, observer: Observer<T>) =
    liveData.observe(this, observer)

internal fun LottieAnimationView.showAnimation(show: Boolean) {
    if (show) {
        show()
        playAnimation()
    } else {
        hide()
        pauseAnimation()
    }
}

internal fun View.hide() {
    visibility = GONE
}

internal fun View.show() {
    visibility = VISIBLE
}

internal fun Fragment.showItemSelectorDialog(
    list: List<String>,
    callback: OnItemSelected,
    @StringRes title: Int
) {
    ItemSelectorDialog.showDialogSelector(
        items = list,
        callbackInterface = callback,
        title = title
    ).show(childFragmentManager, ItemSelectorDialog::javaClass.name)
}

internal fun Fragment.setToolbarTitle(@StringRes title: Int) {
    (activity as? MainActivity)
        ?.findViewById<TextView>(R.id.textViewToolbarTitle)
        ?.text = getString(title)
}

internal fun ImageView.loadImage(url: String?) {
    Glide.with(this)
        .load(url)
        .fitCenter()
        .transition(DrawableTransitionOptions.withCrossFade())
        .into(this)
}

internal fun Context.goToLink(url: String?) {
    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
}


