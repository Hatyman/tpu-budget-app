package ru.tpu.budgetapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.widget.BaseAdapter

interface IUnitWithId {
    var id: Int
}

abstract class AbstractListAdapter<T: IUnitWithId>(protected val context: Context, protected var listData: MutableList<T> = mutableListOf()) :
    BaseAdapter() {
    protected var inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    override fun getCount(): Int {
        return listData.size
    }

    override fun getItem(position: Int): T {
        return listData[position]
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.toLong()
    }

    fun setItems(newItems: MutableList<T>) {
        listData = newItems
        notifyDataSetChanged()
    }

    fun addItem(newItem: T) {
        listData.add(newItem)
        notifyDataSetChanged()
    }

    fun deleteItemOnPosition(position: Int) {
        listData.removeAt(position)
        notifyDataSetChanged()
    }

}