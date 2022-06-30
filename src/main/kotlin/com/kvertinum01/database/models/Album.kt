package com.kvertinum01.database.models

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ResultRow

object AlbumTable: LongIdTable("album") {
    val name = varchar("name", 25)
    val password = varchar("password", 50)
}

data class Album(
    val id: Long = -1,
    val name: String,
    val password: String,
)

fun AlbumTable.rowToAlbum(row: ResultRow): Album = Album(
    id = row[id].value,
    name = row[name],
    password = row[password],
)
