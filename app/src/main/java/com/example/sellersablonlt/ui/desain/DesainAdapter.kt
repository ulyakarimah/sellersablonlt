package com.example.sellersablonlt.ui.desain

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sellersablonlt.databinding.ItemDesainBinding
import com.example.sellersablonlt.db.Desain


class DesainAdapter(val activity: Activity, val listener: (Desain) -> Unit) :
    RecyclerView.Adapter<DesainAdapter.ListViewHolder>() {
    var list = listOf<Desain>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ListViewHolder(private val binding: ItemDesainBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Desain) {
            binding.harga.text = item.harga
            binding.namaDesain.text = item.name
            with(itemView) {
                Glide.with(this)
                    .load(item.image)
                    .into(binding.fotoProduk)
            }

            itemView.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemDesainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}