package com.faridnia.mylibrary.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.faridnia.mylibrary.data.BooksItem
import com.faridnia.mylibrary.databinding.BookListItemBinding

class BooksAdapter : ListAdapter<BooksItem, BooksAdapter.BooksViewHolder>(DiffUtilCallback()) {

    class BooksViewHolder(private val binding: BookListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(booksItem: BooksItem) {
            binding.apply {
                titleTextView.text = booksItem.title
                authorTextView.text = booksItem.author
                genreTextView.text = booksItem.genre
                yearPublishedTextView.text = booksItem.yearPublished.toString()
            }
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<BooksItem>() {
        override fun areItemsTheSame(oldItem: BooksItem, newItem: BooksItem) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: BooksItem, newItem: BooksItem) = oldItem == newItem

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksViewHolder {
        val binding =
            BookListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BooksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}