package com.wahidabd.githubapps.data.model

data class Search(
    val total_count: Long,
    val incomplete_results: Boolean,
    val items: List<User>
)