package com.dg.eqs.page.events

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dg.eqs.R
import com.dg.eqs.base.extension.onClick
import com.dg.eqs.base.extension.setHtml
import com.dg.eqs.base.extension.toggleVisibleGone
import com.dg.eqs.core.visualization.DraftPanel


class EventsList(context: Context, attrs: AttributeSet)
    : RecyclerView(context, attrs) {

    private val eventsAdapter = EventsAdapter()


    init {
        setHasFixedSize(true)

        adapter = eventsAdapter

        layoutManager = LinearLayoutManager(context)
    }

    fun showItems(items: List<EventItem>) = eventsAdapter.submitList(items)

    fun onItemClick(action: (Int) -> Unit) = eventsAdapter.onItemClick(action)

    private class EventsAdapter
        : ListAdapter<EventItem,
            ItemViewHolder>(EventItemDiffer) {

        private var onItemClickAction: ((Int) -> Unit)? = null


        fun onItemClick(action: (Int) -> Unit) {
            onItemClickAction = action
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                ItemViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_event, parent, false))

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = getItem(position)

            holder.item.onClick { onItemClickAction?.invoke(position) }
            holder.divider.toggleVisibleGone(position != 0)
            holder.panel.showDraft(item.exercise)
            holder.playerScore.setHtml(item.playerScore)
            holder.topScore.setHtml(item.topScore)
        }
    }

    private class ItemViewHolder(val item: View) : ViewHolder(item) {
        val divider: ImageView = item.findViewById(R.id.divider)
        val panel: DraftPanel = item.findViewById(R.id.panel)
        val playerScore: TextView = item.findViewById(R.id.playerScore)
        val topScore: TextView = item.findViewById(R.id.topScore)
    }
}