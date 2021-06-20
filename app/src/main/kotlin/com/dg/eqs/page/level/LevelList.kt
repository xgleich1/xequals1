package com.dg.eqs.page.level

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
import com.dg.eqs.base.extension.setText
import com.dg.eqs.base.extension.toggleVisibleGone
import com.dg.eqs.core.visualization.DraftPanel


class LevelList(context: Context, attrs: AttributeSet)
    : RecyclerView(context, attrs) {

    private val levelAdapter = LevelItemAdapter()


    init {
        setHasFixedSize(true)

        adapter = levelAdapter

        layoutManager = LinearLayoutManager(context)
    }

    fun showItems(items: List<LevelItem>) = levelAdapter.submitList(items)

    fun onItemClick(action: (Int) -> Unit) = levelAdapter.onItemClick(action)

    private class LevelItemAdapter
        : ListAdapter<LevelItem,
            ItemViewHolder>(LevelItemDiffer) {

        private var onItemClickAction: ((Int) -> Unit)? = null


        fun onItemClick(action: (Int) -> Unit) {
            onItemClickAction = action
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                ItemViewHolder(LayoutInflater.from(parent.context)
                        .inflate(R.layout.item_level, parent, false))

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val item = getItem(position)

            holder.item.onClick { onItemClickAction?.invoke(position) }
            holder.divider.toggleVisibleGone(position != 0)
            holder.panel.showDraft(item.exercise)
            holder.steps.setText(item.steps)
        }
    }

    private class ItemViewHolder(val item: View) : ViewHolder(item) {
        val divider: ImageView = item.findViewById(R.id.divider)
        val panel: DraftPanel = item.findViewById(R.id.panel)
        val steps: TextView = item.findViewById(R.id.steps)
    }
}