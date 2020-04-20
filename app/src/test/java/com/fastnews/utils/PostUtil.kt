package com.fastnews.utils

import com.fastnews.service.model.PostData
import com.fastnews.service.model.Preview

object PostUtil {
    val oneItemPostList = listOf(
        PostData(
            id = "id",
            author = "",
            thumbnail = "",
            name = "",
            num_comments = 0,
            score = 0,
            title = "",
            created_utc = 1L,
            url = "http://url",
            preview = Preview(emptyList())
        )
    )
    val manyItemsPostList = listOf(
        PostData(
            id = "id 1",
            author = "",
            thumbnail = "",
            name = "",
            num_comments = 0,
            score = 0,
            title = "",
            created_utc = 1L,
            url = "http://url",
            preview = Preview(emptyList())
        ),
        PostData(
            id = "id 2",
            author = "",
            thumbnail = "",
            name = "",
            num_comments = 0,
            score = 0,
            title = "",
            created_utc = 1L,
            url = "http://url",
            preview = Preview(emptyList())
        )
    )
}