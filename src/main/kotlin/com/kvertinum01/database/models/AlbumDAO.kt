package com.kvertinum01.database.models

import com.kvertinum01.tools.strictlySingleOrNull
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class AlbumDAO {
    fun createAlbum(album: Album): Long =
        AlbumTable.insert{
            it[name] = album.name
            it[password] = album.password
        }.resultedValues!!.single()[AlbumTable.id].value

    fun findAlbumByName(name: String): Album? =
        AlbumTable.select { AlbumTable.name eq name }.map { AlbumTable.rowToAlbum(it) }.strictlySingleOrNull()
}
