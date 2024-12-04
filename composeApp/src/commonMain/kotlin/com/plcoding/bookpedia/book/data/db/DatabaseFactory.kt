package com.plcoding.bookpedia.book.data.db

import androidx.room.RoomDatabase

expect class DatabaseFactory {
    fun create(): RoomDatabase.Builder<FavoriteBookDb>
}