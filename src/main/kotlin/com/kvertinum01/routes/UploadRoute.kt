package com.kvertinum01.routes

import com.kvertinum01.REDIRECT_URL
import com.kvertinum01.database.models.AlbumDAO
import com.kvertinum01.database.services.AlbumService
import com.kvertinum01.database.services.AlbumServiceResult
import com.kvertinum01.tools.fileExists
import com.kvertinum01.tools.writeBytes
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.uploadImage() {
    val albumDAO = AlbumDAO()
    val albumService = AlbumService(albumDAO)

    post("/upload") {
        var fileName: String? = null
        var fileAlbum: String? = null
        var fileBytes: ByteArray? = null
        var albumKey: String? = null

        val multipartData = call.receiveMultipart()

        multipartData.forEachPart { part ->
            when(part) {
                is PartData.FormItem -> {
                    when(part.name) {
                        "album-name" -> fileAlbum = part.value
                        "album-key" -> albumKey = part.value
                    }
                }
                is PartData.FileItem -> {
                    fileName = part.originalFileName as String
                    fileBytes = part.streamProvider().readBytes()
                }

                else -> {}
            }
        }

        if (
            fileAlbum == null || fileName == null || albumKey == null
            || !fileExists("/images/$fileAlbum")
        ) {
            call.respond(HttpStatusCode.BadRequest)
            return@post
        }

        when(val albumServiceResult = albumService.getAlbumByName(fileAlbum!!)) {
            is AlbumServiceResult.Failed.AlbumDoesNotExist ->
                call.respond(
                    HttpStatusCode.NotFound,
                    "Album ${albumServiceResult.name} does not exist"
                )
            is AlbumServiceResult.Success.AlbumInfo -> {
                if (albumServiceResult.album.password != albumKey) {
                    call.respond(
                        HttpStatusCode.BadRequest,
                        "Bad album key"
                    )
                    return@post
                }
                writeBytes(fileAlbum!!, fileName!!, fileBytes!!)
                call.respondRedirect(REDIRECT_URL)
            }

            else -> error("Unexpected result $albumServiceResult")
        }
    }
}