package com.plcoding.bookpedia.book.presentation.book_detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class BookDetailViewModel: ViewModel() {

    private val _state = MutableStateFlow(BookDetailState())
    val state = _state

    fun onAction(action: BookDetailAction) {

    }
}