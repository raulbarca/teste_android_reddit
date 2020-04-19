package com.fastnews.utils

import com.fastnews.service.api.RedditService
import com.fastnews.service.model.PostChildren
import com.fastnews.service.model.PostData
import com.fastnews.service.model.PostDataChild
import com.fastnews.service.model.PostResponse
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import retrofit2.Response

fun mockServiceSuccess(itemList: List<PostData>) = mockk<RedditService> {
    val postResponse = PostResponse(
        data = PostDataChild(
            children = itemList.map { PostChildren(it) },
            after = "after",
            before = "before"
        )
    )
    val response: Response<PostResponse> = mockk()
    every { response.body() } returns postResponse
    every { response.isSuccessful } returns true
    coEvery { getTop(any()).await() } returns response
    coEvery { getTopAfter(any(), any()).await() } returns response
    coEvery { getTopBefore(any(), any()).await() } returns response
}

fun mockServiceFailure() = mockk<RedditService> {
    coEvery { getTop(any()).await() } throws Error()
    coEvery { getTopAfter(any(), any()).await() } throws Error()
    coEvery { getTopBefore(any(), any()).await() } throws Error()
}