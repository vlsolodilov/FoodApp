package com.solodilov.foodapp.presentation.menu

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.solodilov.foodapp.R
import com.solodilov.foodapp.databinding.ItemMealBinding
import com.solodilov.foodapp.domain.entity.Meal

class MealAdapter : ListAdapter<Meal, MealViewHolder>(MealDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder =
        MealViewHolder(ItemMealBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
        Log.d("TAG", "onBindViewHolder: ")
    }
}

class MealViewHolder(
    private val binding: ItemMealBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(meal: Meal) {

        binding.apply {
            mealTitle.text = meal.name
            mealDescription.text = meal.description
            mealStartPrice.text = itemView.context.getString(
                R.string.price_format,
                meal.price
            )
        }

        Glide
            .with(itemView)
            .load(meal.image)
            .into(binding.mealImage)

    }
}

private class MealDiffCallback : DiffUtil.ItemCallback<Meal>() {

    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
        return oldItem == newItem
    }
}
