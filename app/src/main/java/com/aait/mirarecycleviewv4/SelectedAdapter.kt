package com.aait.mirarecycleviewv4

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aait.mirarecycleviewv4.databinding.Item3Binding

class SelectedAdapter() : RecyclerView.Adapter<SelectedAdapter.ViewHolder>() {

    var list: MutableList<Int> = ArrayList()

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = Item3Binding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    class ViewHolder(val binding: Item3Binding) :
        RecyclerView.ViewHolder(binding.root)
}
