package com.kvertinum01.plugins

import com.kvertinum01.routes.createAlbum
import com.kvertinum01.routes.randomImage
import com.kvertinum01.routes.uploadImage
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        randomImage()
        uploadImage()
        createAlbum()
    }
}
