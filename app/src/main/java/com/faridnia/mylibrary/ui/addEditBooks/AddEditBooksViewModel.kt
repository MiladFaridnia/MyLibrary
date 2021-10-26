package com.faridnia.mylibrary.ui.addEditBooks

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.faridnia.mylibrary.data.BooksDao
import com.faridnia.mylibrary.data.BooksItem

class AddEditBooksViewModel @ViewModelInject constructor(
    private val dao: BooksDao,
    @Assisted private val state : SavedStateHandle
) : ViewModel() {

    val book = state.get<BooksItem>("book")


}