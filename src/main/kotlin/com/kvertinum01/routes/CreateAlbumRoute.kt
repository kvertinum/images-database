package com.kvertinum01.routes

import com.kvertinum01.REDIRECT_URL
import com.kvertinum01.database.models.AlbumDAO
import com.kvertinum01.database.services.AlbumService
import com.kvertinum01.database.services.AlbumServiceResult
import com.kvertinum01.tools.makeDir
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.createAlbum() {
    val albumDAO = AlbumDAO()
    val albumService = AlbumService(albumDAO)

    post("/create") {
        val formParams = call.receiveParameters()

        val albumName = formParams["album-name"]
        val albumKey = formParams["album-key"]
        val albumKeyRepeat = formParams["album-key-repeat"]

        if (
            albumName == null || albumKey == null || albumKeyRepeat == null
            || albumKey != albumKeyRepeat
        ) {
            call.respond(HttpStatusCode.BadRequest, "Bad request")
            return@post
        }

        when(val albumServiceResult = albumService.createAlbum(albumName, albumKey)) {
            is AlbumServiceResult.Failed.NameAlreadyTaken ->
                call.respond(HttpStatusCode.BadRequest, "Name is already taken")
            is AlbumServiceResult.Failed.NameTooLong ->
                call.respond(HttpStatusCode.BadRequest, "Name cannot exceed 25 characters")
            is AlbumServiceResult.Failed.PasswordTooLong ->
                call.respond(HttpStatusCode.BadRequest, "Key cannot exceed 50 characters")

            is AlbumServiceResult.Success.AlbumCreated -> {
                makeDir(albumName)
                call.respondRedirect(REDIRECT_URL)
            }

            else -> error("Unexpected result $albumServiceResult")
        }
    }
}