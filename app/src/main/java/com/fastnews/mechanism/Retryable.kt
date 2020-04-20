package com.fastnews.mechanism

interface Retryable {
    fun start() = retry()
    fun retry()
}