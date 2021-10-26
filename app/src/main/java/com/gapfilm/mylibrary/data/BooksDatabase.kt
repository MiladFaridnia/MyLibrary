package com.gapfilm.mylibrary.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [BooksItem::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDao
}