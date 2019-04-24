package br.com.mdr.testegitapi.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.mdr.testegitapi.interfaces.AdapterItemsContract

class BindingAdapters {

    companion object {
        @BindingAdapter("itens")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, items: MutableList<Any>) {

            recyclerView.adapter.let {
                if (it is AdapterItemsContract) {
                    it.replaceItens(items)
                }
            }
        }
    }

}