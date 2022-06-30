package com.kvertinum01.tools

fun <T> Collection<T>.strictlySingleOrNull(): T? = when (size) {
    0 -> null
    1 -> first()
    else -> error("Expected at most one element but found $size")
}
