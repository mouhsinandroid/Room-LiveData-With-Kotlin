package com.mouhsinbourqaiba.sample.roomwordsample.repository

import androidx.lifecycle.LiveData
import com.mouhsinbourqaiba.sample.roomwordsample.database.Word
import com.mouhsinbourqaiba.sample.roomwordsample.database.WordDao

class WordRepository(private val wordDao: WordDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<Word>> = wordDao.getAllWords()


    // The suspend modifier tells the compiler that this must be called from a
    // coroutine or another suspend function.
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}