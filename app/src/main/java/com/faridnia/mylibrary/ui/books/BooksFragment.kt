package com.faridnia.mylibrary.ui.books

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.faridnia.mylibrary.R
import com.faridnia.mylibrary.data.BooksItem
import com.faridnia.mylibrary.databinding.BooksFragmentBinding
import com.faridnia.mylibrary.util.exhaustive
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BooksFragment : Fragment(R.layout.books_fragment), BooksAdapter.OnBookClicked {

    private val viewModel: BooksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = BooksFragmentBinding.bind(view)

        val booksAdapter = BooksAdapter(this)

        binding.apply {
            booksRecyclerView.apply {
                adapter = booksAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)

            }

            val touchHelper = ItemTouchHelper(object :
                ItemTouchHelper.SimpleCallback(
                    0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) {

                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val task = booksAdapter.currentList[viewHolder.adapterPosition]
                    viewModel.onBookSwiped(task)
                }
            })

            touchHelper.attachToRecyclerView(booksRecyclerView)

            addBookFloatingActionButton.setOnClickListener {
                viewModel.onAddNewBookClicked()
            }

        }

        viewModel.books.observe(viewLifecycleOwner) {
            booksAdapter.submitList(it)
        }


        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect { event ->
                when (event) {
                    is BooksViewModel.BookEvent.ShowDeleteBookMessage -> {
                        showSnakeBar("Book deleted")
                    }
                    is BooksViewModel.BookEvent.ShowAddBookFragment -> {
                        showSnakeBar("Add new book")
                        val action =
                            BooksFragmentDirections.actionBooksFragmentToAddEditBooksFragment(null)
                        findNavController().navigate(action)
                    }
                    is BooksViewModel.BookEvent.ShowEditBookFragment -> {
                        val action =
                            BooksFragmentDirections.actionBooksFragmentToAddEditBooksFragment(event.book)
                        findNavController().navigate(action)
                    }
                }.exhaustive
            }
        }

    }

    private fun showSnakeBar(message: String) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG)
            .show()
    }

    override fun onBookItemClicked(booksItem: BooksItem) {
        viewModel.onEditBookFragment(booksItem)
    }


}