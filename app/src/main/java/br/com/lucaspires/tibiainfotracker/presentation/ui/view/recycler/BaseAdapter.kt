package br.com.lucaspires.tibiainfotracker.presentation.ui.view.recycler

import androidx.recyclerview.widget.RecyclerView

internal abstract class BaseAdapter<I, VH : RecyclerView.ViewHolder?> : RecyclerView.Adapter<VH>() {

    protected val listOfItems = arrayListOf<I>()

    fun addItems(list: List<I>) {
        listOfItems.clear()
        listOfItems.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = listOfItems.size
}