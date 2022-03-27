package com.example.coolbootsdemo.model

data class Response(
    val current_page: String,
    val `data`: List<Data>,
    val first_page_url: String,
    val from: String,
    val next_page_url: String,
    val path: String,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)