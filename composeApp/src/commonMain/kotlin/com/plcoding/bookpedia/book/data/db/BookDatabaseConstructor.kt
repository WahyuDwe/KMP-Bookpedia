package com.plcoding.bookpedia.book.data.db

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BookDatabaseConstructor: RoomDatabaseConstructor<FavoriteBookDb> {
    override fun initialize(): FavoriteBookDb
}