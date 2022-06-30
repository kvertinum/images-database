package com.kvertinum01.database.services

import com.kvertinum01.database.models.Album
import com.kvertinum01.database.models.AlbumDAO
import com.kvertinum01.database.factory.DatabaseFactory.dbQuery

class AlbumService(private val albumDAO: AlbumDAO) {
    suspend fun getAlbumByName(name: String): AlbumServiceResult = dbQuery{
        val album = albumDAO.findAlbumByName(name)
            ?: return@dbQuery AlbumServiceResult.Failed.AlbumDoesNotExist(name)

        AlbumServiceResult.Success.AlbumInfo(album)
    }

    suspend fun createAlbum(name: String, password: String): AlbumServiceResult = dbQuery {
        when {
            name.length > 25 -> AlbumServiceResult.Failed.NameTooLong(name)
            password.length > 50 -> AlbumServiceResult.Failed.PasswordTooLong(password)
            albumDAO.findAlbumByName(name) != null -> AlbumServiceResult.Failed.NameAlreadyTaken(name)

            else -> {
                val albumID = albumDAO.createAlbum(
                    Album(name = name, password = password)
                )

                AlbumServiceResult.Success.AlbumCreated(albumID)
            }
        }
    }
}