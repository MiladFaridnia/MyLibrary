package com.faridnia.mylibrary.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.faridnia.mylibrary.di.ApplicationScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [BooksItem::class], version = 1)
abstract class BooksDatabase : RoomDatabase() {

    abstract fun booksDao(): BooksDao

    class Callback @Inject constructor(
        private val database: Provider<BooksDatabase>,
        @ApplicationScope private val applicationScope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            val dao = database.get().booksDao()

            //add sample data
            applicationScope.launch {
                dao.insert(
                    BooksItem(
                        title = "Test Book 1",
                        author = "Test Author 1",
                        genre = "fiction",
                        yearPublished = 1987
                    )
                )
                dao.insert(
                    BooksItem(
                        title = "Test Book 2",
                        author = "Test Author 2",
                        genre = "crime",
                        yearPublished = 1985
                    )
                )
                dao.insert(
                    BooksItem(
                        title = "Test Book 3",
                        author = "Test Author 3",
                        genre = "sci-fi",
                        yearPublished = 1968
                    )
                )
                dao.insert(
                    BooksItem(
                        title = "Test Book 4",
                        author = "Test Author 4",
                        genre = "history",
                        yearPublished = 1999
                    )
                )
                dao.insert(
                    BooksItem(
                        title = "Test Book 5",
                        author = "Test Author 5",
                        genre = "war",
                        yearPublished = 1844
                    )
                )
                dao.insert(
                    BooksItem(
                        title = "Test Book 6",
                        author = "Test Author 6",
                        genre = "action",
                        yearPublished = 1997
                    )
                )
                dao.insert(
                    BooksItem(
                        title = "Test Book 7",
                        author = "Test Author 7",
                        genre = "dram",
                        yearPublished = 1996
                    )
                )
                dao.insert(
                    BooksItem(
                        title = "Test Book 8",
                        author = "Test Author 8",
                        genre = "fiction",
                        yearPublished = 1990
                    )
                )
            }
        }
    }

}