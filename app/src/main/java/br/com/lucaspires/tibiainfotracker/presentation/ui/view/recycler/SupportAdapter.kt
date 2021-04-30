package br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import br.com.lucaspires.tibiainfotracker.databinding.RecyclerViewItemSupportBinding
import br.com.lucaspires.tibiainfotracker.domain.model.support.SupportModel
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.goToLink
import br.com.lucaspires.tibiainfotracker.presentation.ui.extension.loadImage

internal class SupportAdapter : BaseAdapter<SupportModel, SupportViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SupportViewHolder {
        return SupportViewHolder(
            RecyclerViewItemSupportBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SupportViewHolder, position: Int) {
        val item = listOfItems[position]
        with(holder.binding) {
            textViewSuppName.text = item.name
            textViewLinkSupp.run {
                setText(item.url?.replace("https://", "www."))
                setOnClickListener { context.goToLink(item.url) }
                imageViewSuppLogo.loadImage(item.imgUrl)
            }
        }
    }
}