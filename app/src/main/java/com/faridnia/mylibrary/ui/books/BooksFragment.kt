package com.faridnia.mylibrary.ui.books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.faridnia.mylibrary.R
import com.faridnia.mylibrary.databinding.BooksFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksFragment : Fragment(R.layout.books_fragment) {

    private val viewModel: BooksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = BooksFragmentBinding.bind(view)

        val booksAdapter = BooksAdapter()

        binding.apply {
            booksRecyclerView.apply {
                adapter = booksAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)

            }
        }

        viewModel.books.observe(viewLifecycleOwner) {
            booksAdapter.submitList(it)
        }
    }


}