package com.plcoding.bookpedia.book.data.mapper

import com.plcoding.bookpedia.book.data.db.BookEntity
import com.plcoding.bookpedia.book.data.dto.SearchedBookDto
import com.plcoding.bookpedia.book.domain.Book

fun SearchedBookDto.toBook(): Book {
    return Book(
        id = id.substringAfterLast("/"),
        title = title,
        imageUrl = if (coverKey != null) {
            "https://covers.openlibrary.org/b/olid/${coverKey}-L.jpg"
        } else {
            "https://covers.openlibrary.org/b/id/${covertAlternativeKey}-L.jpg"
        },
        authors = authorName ?: emptyList(),
        description = null,
        languages = language ?: emptyList(),
        firstPublishYear = firstPublishYear.toString(),
        averageRating = ratingAverage,
        ratingCount = ratingCount,
        numPages = numPagesMedian,
        numEditions = numEdition ?: 0,
    )
}

fun Book.toBookEntity(): BookEntity {
    return BookEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishYear = firstPublishYear,
        ratingAverage = averageRating,
        ratingCount = ratingCount,
        numPagesMedian = numPages,
        numEditions = numEditions
    )
}

fun BookEntity.toBook(): Book {
    return Book(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl,
        languages = languages,
        authors = authors,
        firstPublishYear = firstPublishYear,
        averageRating = ratingAverage,
        ratingCount = ratingCount,
        numPages = numPagesMedian,
        numEditions = numEditions
    )
}


