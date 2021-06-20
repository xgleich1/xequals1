package com.dg.eqs.page.events

import androidx.recyclerview.widget.DiffUtil


object EventItemDiffer : DiffUtil.ItemCallback<EventItem>() {
    override fun areItemsTheSame(oldItem: EventItem, newItem: EventItem) =
            oldItem.exercise == newItem.exercise

    override fun areContentsTheSame(oldItem: EventItem, newItem: EventItem) =
            oldItem == newItem
}