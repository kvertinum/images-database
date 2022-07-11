package com.kvertinum01.database.services

import com.kvertinum01.database.models.Album

sealed class AlbumServiceResult {
    sealed class Failed : AlbumServiceResult() {
        class AlbumDoesNotExist(val name: String) : Failed()
        class NameTooLong(val name: String) : Failed()
        class PasswordTooLong(val password: String) : Failed()
        class NameAlreadyTaken(val name: String) : Failed()
    }

    sealed class Success : AlbumServiceResult() {
        class AlbumInfo(val album: Album) : Success()
        class AlbumCreated(val id: Long) : Success()
    }
}