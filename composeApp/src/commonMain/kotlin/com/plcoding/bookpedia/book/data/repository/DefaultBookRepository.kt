package com.plcoding.bookpedia.book.data.repository

import androidx.sqlite.SQLiteException
import com.plcoding.bookpedia.book.data.db.FavoriteBookDao
import com.plcoding.bookpedia.book.data.mapper.toBook
import com.plcoding.bookpedia.book.data.mapper.toBookEntity
import com.plcoding.bookpedia.book.data.network.RemoteBookDateSource
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.EmptyResult
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultBookRepository(
    private val remoteBookDateSource: RemoteBookDateSource,
    private val favoriteBookDao: FavoriteBookDao
) : BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDateSource
            .searchBook(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDetails(bookId: String): Result<String?, DataError> {
        val localResult = favoriteBookDao.getFavoriteBookById(bookId)
        return if (localResult != null){
            remoteBookDateSource
                .getBookDetails(bookId)
                .map { it.description }
        } else {
            Result.Success(localResult?.description)
        }
    }

    override fun getFavoriteBooks(): Flow<List<Book>> {
        return favoriteBookDao
            .getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.map { it.toBook() }
            }
    }

    override fun isBookFavorite(bookId: String): Flow<Boolean> {
        return favoriteBookDao.getFavoriteBooks()
            .map { bookEntities ->
                bookEntities.any { it.id == bookId }
            }
    }

    override suspend fun markAsFavorite(book: Book): EmptyResult<DataError.Local> {
        return try {
            favoriteBookDao.upsert(book.toBookEntity())
            Result.Success(Unit)
        } catch (E: SQLiteException) {
            Result.Error(DataError.Local.DISK_FULL)
        }
    }

    override suspend fun deleteFromFavorites(bookId: String) {
        favoriteBookDao.deleteFavoriteBookById(bookId)
    }
}