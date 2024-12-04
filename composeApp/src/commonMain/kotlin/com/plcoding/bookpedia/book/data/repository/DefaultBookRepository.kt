package com.plcoding.bookpedia.book.data.repository

import com.plcoding.bookpedia.book.data.mapper.toBook
import com.plcoding.bookpedia.book.data.network.RemoteBookDateSource
import com.plcoding.bookpedia.book.domain.Book
import com.plcoding.bookpedia.book.domain.BookRepository
import com.plcoding.bookpedia.core.domain.DataError
import com.plcoding.bookpedia.core.domain.Result
import com.plcoding.bookpedia.core.domain.map

class DefaultBookRepository(
    private val remoteBookDateSource: RemoteBookDateSource,
): BookRepository {

    override suspend fun searchBooks(query: String): Result<List<Book>, DataError.Remote> {
        return remoteBookDateSource
            .searchBook(query)
            .map { dto ->
                dto.results.map { it.toBook() }
            }
    }

    override suspend fun getBookDetails(bookWorkId: String): Result<String?, DataError> {
        return remoteBookDateSource
            .getBookDetails(bookWorkId)
            .map { it.description }
    }
}