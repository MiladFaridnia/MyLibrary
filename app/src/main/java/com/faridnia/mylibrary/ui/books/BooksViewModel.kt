package com.faridnia.mylibrary.ui.books

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.faridnia.mylibrary.data.BooksDao
import com.faridnia.mylibrary.data.BooksItem
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class BooksViewModel @ViewModelInject constructor(private val dao: BooksDao) : ViewModel() {

    val books = dao.getBooks().asLiveData()

    private val eventChannel = Channel<BookEvent>()

    val eventFlow = eventChannel.receiveAsFlow()

    fun onBookSwiped(booksItem: BooksItem) = viewModelScope.launch {
        dao.delete(book = booksItem)
        eventChannel.send(BookEvent.ShowDeleteBookMessage(booksItem))
    }

    fun onAddNewBookClicked() = viewModelScope.launch {
        eventChannel.send(BookEvent.ShowAddBookFragment)
    }

    fun onEditBookFragment(book: BooksItem)= viewModelScope.launch {
        eventChannel.send(BookEvent.ShowEditBookFragment(book))
    }

    sealed class BookEvent {
        object ShowAddBookFragment : BookEvent()
        data class ShowEditBookFragment(val book: BooksItem) : BookEvent()
        data class ShowDeleteBookMessage(val book: BooksItem) : BookEvent()
    }

}