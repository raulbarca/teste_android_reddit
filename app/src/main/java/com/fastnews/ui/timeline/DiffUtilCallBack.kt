package com.fastnews.ui.timeline

import androidx.recyclerview.widget.DiffUtil
import com.fastnews.service.model.PostData

class DiffUtilCallBack : DiffUtil.ItemCallback<PostData>() {
    override fun areItemsTheSame(oldItem: PostData, newItem: PostData): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostData, newItem: PostData): Boolean {
        return oldItem.title == newItem.title
                && oldItem.score == newItem.score
                && oldItem.num_comments == newItem.num_comments
    }
}