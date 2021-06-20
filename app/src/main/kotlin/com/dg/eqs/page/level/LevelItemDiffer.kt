package com.dg.eqs.page.level

import androidx.recyclerview.widget.DiffUtil


object LevelItemDiffer : DiffUtil.ItemCallback<LevelItem>() {
    override fun areItemsTheSame(oldItem: LevelItem, newItem: LevelItem) =
            oldItem.exercise == newItem.exercise

    override fun areContentsTheSame(oldItem: LevelItem, newItem: LevelItem) =
            oldItem == newItem
}