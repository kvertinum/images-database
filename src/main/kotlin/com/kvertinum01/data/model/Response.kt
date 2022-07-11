package com.kvertinum01.data.model

@kotlinx.serialization.Serializable
data class Response(
    val status: String,
    val content: String,
)