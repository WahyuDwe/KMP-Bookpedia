package com.plcoding.bookpedia.book.data.db

import androidx.room.RoomDatabaseConstructor

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect class BookDatabaseConstructor: RoomDatabaseConstructor<FavoriteBookDb> {
    override fun initialize(): FavoriteBookDb
}