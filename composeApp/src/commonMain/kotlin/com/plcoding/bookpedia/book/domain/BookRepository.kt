package com.plcoding.bookpedia.book.domain

import com.plcoding.bookpedia.book.data.db.BookEntity
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result
import kotlinx.coroutines.flow.Flow

interface BookRepository {
    suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote>
    suspend fun getBookDetails(bookWorkId: String): Result<String?, DataError>

    fun getFavoriteBooks(): Flow<List<Book>>
    fun isBookFavorite(bookId: String): Flow<Boolean>
    suspend fun markAsFavorite(book: Book)
    suspend fun deleteFromFavorites(bookId: String)
}