package com.solodilov.foodapp.presentation.menu

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.solodilov.foodapp.databinding.ItemBannerBinding
import com.solodilov.foodapp.databinding.ItemMealBinding
import com.solodilov.foodapp.domain.entity.Meal

class BannerAdapter : ListAdapter<Int, BannerViewHolder>(BannerDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder =
        BannerViewHolder(ItemBannerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class BannerViewHolder(
    private val binding: ItemBannerBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(url: Int) {
        binding.banner.setImageResource(url)
    }
}

private class BannerDiffCallback : DiffUtil.ItemCallback<Int>() {

    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}
