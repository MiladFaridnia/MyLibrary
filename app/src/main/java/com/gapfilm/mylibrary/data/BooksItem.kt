package com.gapfilm.mylibrary.data


import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
@Parcelize
data class BooksItem(
    val author: String = "",
    val checkedOut: Boolean = false,
    val createdAt: String = "",
    val genre: String = "",
    val id: String = "",
    val title: String = "",
    val yearPublished: Int = 0,
    @PrimaryKey(autoGenerate = true) val rId: Int = 0
) : Parcelable