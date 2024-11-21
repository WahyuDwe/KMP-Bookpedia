package com.plcoding.bookpedia.book.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchedBookDto(
    @SerialName("key") val id: String,
    @SerialName("title") val title: String,
    @SerialName("language") val language: List<String>? = null,
    @SerialName("cover_i") val covertAlternativeKey: Int? = null,
    @SerialName("author_key") val authorKey: List<String>? = null,
    @SerialName("author_name") val authorName: List<String>? = null,
    @SerialName("cover_edition_key") val coverKey: String? = null,
    @SerialName("first_publish_year") val firstPublishYear: Int? = null,
    @SerialName("ratings_average") val ratingAverage: Double? = null,
    @SerialName("ratings_count") val ratingCount: Int? = null,
    @SerialName("number_of_pages_median") val numPagesMedian: Int? = null,
    @SerialName("edition_count") val numEdition: Int? = null,
)
