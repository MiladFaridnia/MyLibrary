package com.gapfilm.mylibrary.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BooksDao {

    @Query("SELECT * FROM book_table")
    fun getBooks() : Flow<List<BooksItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: BooksItem)

    @Update
    suspend fun update(book: BooksItem)

    @Delete
    suspend fun delete(book: BooksItem)


}