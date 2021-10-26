package com.faridnia.mylibrary.ui.books

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.faridnia.mylibrary.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksFragment : Fragment(R.layout.books_fragment) {

    private val viewModel: BooksViewModel by viewModels()

}