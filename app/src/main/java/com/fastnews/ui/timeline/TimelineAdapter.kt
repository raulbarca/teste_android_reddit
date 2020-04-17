package com.fastnews.ui.timeline

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagedListAdapter
import com.fastnews.R
import com.fastnews.service.model.PostData
import kotlinx.android.synthetic.main.include_item_timeline_thumbnail.view.*

class TimelineAdapter(
    val onClickItem: (PostData, ImageView) -> Unit
) : PagedListAdapter<PostData, TimelineItemViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimelineItemViewHolder =
        TimelineItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_timeline,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TimelineItemViewHolder, position: Int) {
        holder.data = getItem(position)
        holder.view.setOnClickListener {
            getItem(position)?.let { item ->
                onClickItem(
                    item,
                    holder.view.item_timeline_thumbnail
                )
            }
        }
    }
}