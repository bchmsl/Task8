package com.bchmsl.task8.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bchmsl.task8.R
import com.bchmsl.task8.common.extensions.setImage
import com.bchmsl.task8.databinding.LayoutItemBinding
import com.bchmsl.task8.presentation.model.ItemModel

class ItemsAdapter : ListAdapter<ItemModel, ItemsAdapter.ItemsViewHolder>(itemCallback) {

    var favoriteClickCallback: ((ItemModel, Int) -> Unit)? = null
    inner class ItemsViewHolder(private val binding: LayoutItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val currentItem = getItem(adapterPosition)
            binding.apply {
                tvTitle.text = currentItem.title
                currentItem.cover?.let { ivImageValue.setImage(it) }
                tvPrice.text = currentItem.price
                ivFavorite.setImageResource(imageLikedValue(currentItem))
                ivFavorite.setOnClickListener { favoriteClickCallback?.invoke(currentItem, adapterPosition) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsViewHolder {
        return ItemsViewHolder(
            LayoutItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemsViewHolder, position: Int) {
        holder.bind()
    }

    private fun imageLikedValue(item: ItemModel): Int {
        return if (item.liked == true) R.drawable.ic_favorite_filled
        else R.drawable.ic_favorite_not_filled
    }

    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<ItemModel>() {
            override fun areItemsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: ItemModel, newItem: ItemModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}