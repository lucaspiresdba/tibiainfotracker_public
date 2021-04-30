package br.com.lucaspires.tibiainfotracker.presentation.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import br.com.lucaspires.tibiainfotracker.databinding.DialogItemSelectorBinding
import br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler.ItemSelectorAdapter

interface OnItemSelected {
    fun setItem(item: String)
}

class ItemSelectorDialog : DialogFragment() {

    companion object {
        fun showDialogSelector(
            items: List<String>,
            callbackInterface: OnItemSelected,
            @StringRes title: Int
        ): ItemSelectorDialog {
            return ItemSelectorDialog().apply {
                itemSelectorList = items
                callback = callbackInterface
                dialogTitle = title
            }
        }
    }

    private lateinit var binding: DialogItemSelectorBinding
    private val adapter = ItemSelectorAdapter {
        itemSelectedRecycler(it)
    }

    var itemSelectorList = emptyList<String>()
    var callback: OnItemSelected? = null

    @StringRes
    var dialogTitle: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogItemSelectorBinding.inflate(inflater)
        dialog?.window?.let {
            it.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            it.requestFeature(Window.FEATURE_NO_TITLE)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        binding.recyclerViewItemSelector.adapter = adapter
        adapter.addItems(itemSelectorList)
        dialogTitle?.let {
            binding.textViewChoseItem.text = getString(it)
        }
    }

    private fun itemSelectedRecycler(item: String) {
        callback?.setItem(item)
        dismissAllowingStateLoss()
    }
}