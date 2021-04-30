package br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.lucaspires.tibiainfotracker.databinding.RecyclerViewItemSelectorBinding

internal class ItemSelectorAdapter(private val func: (String) -> Unit) :
    BaseAdapter<String, ItemSelectorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSelectorViewHolder {
        return ItemSelectorViewHolder(
            RecyclerViewItemSelectorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemSelectorViewHolder, position: Int) {
        with(holder.binding) {
            textViewitemSelector.text = listOfItems[position]
            root.setOnClickListener { func.invoke(textViewitemSelector.text.toString()) }
        }
    }
}