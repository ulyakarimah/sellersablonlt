package com.example.sellersablonlt.ui.toko

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sellersablonlt.databinding.ItemTokoBinding
import com.example.sellersablonlt.db.Toko

class TokoAdapter(val activity: Activity, val listener: (Toko) -> Unit) :
    RecyclerView.Adapter<TokoAdapter.ListViewHolder>() {
    var list = listOf<Toko>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ListViewHolder(private val binding: ItemTokoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Toko) {
            binding.namaToko.text = item.name
            binding.alamat.text = item.alamat

            with(itemView) {
                Glide.with(this)
                    .load(item.image)
                    .into(binding.fotoToko)
            }
            itemView.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemTokoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}