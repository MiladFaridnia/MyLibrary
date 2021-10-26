package com.faridnia.mylibrary.ui.addEditBooks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.faridnia.mylibrary.R
import com.faridnia.mylibrary.databinding.AddEditBookFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddEditBooksFragment : Fragment(R.layout.add_edit_book_fragment) {

    private val viewModel: AddEditBooksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = AddEditBookFragmentBinding.bind(view)

        binding.apply {
            viewModel.book?.let { bookItem ->
                titleEditText.setText(bookItem.title)
                authorEditText.setText(bookItem.author)
                genreEditText.setText(bookItem.genre)
                yearPublishedEditText.setText(bookItem.yearPublished.toString())
            }

        }
    }

}