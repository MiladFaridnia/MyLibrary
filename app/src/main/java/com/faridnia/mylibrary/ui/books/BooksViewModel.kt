package com.faridnia.mylibrary.ui.books

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.faridnia.mylibrary.data.BooksDao

class BooksViewModel @ViewModelInject constructor(private val dao: BooksDao) : ViewModel() {

}