package com.kvertinum01.routes

import com.kvertinum01.tools.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

fun Route.randomImage() {
    get("/album/{album_id}") {
        val albumID = call.parameters["album_id"]
        if (albumID != null && fileExists("/images/$albumID")) {
            val html = getListFiles("/images/$albumID")
            if (html.isNotEmpty()) {
                val randFile = File(html.random())
                call.respondBytes(randFile.readBytes())
            }
        }
    }
}