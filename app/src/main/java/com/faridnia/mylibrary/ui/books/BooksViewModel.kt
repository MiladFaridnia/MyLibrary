package com.faridnia.mylibrary.ui.books

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faridnia.mylibrary.data.BooksDao

class BooksViewModel @ViewModelInject constructor(private val dao: BooksDao) : ViewModel() {

    val books = dao.getBooks().asLiveData()

}