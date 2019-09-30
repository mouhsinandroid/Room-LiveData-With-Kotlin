package com.mouhsinbourqaiba.sample.roomwordsample.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.mouhsinbourqaiba.sample.roomwordsample.database.Word
import com.mouhsinbourqaiba.sample.roomwordsample.database.WordRoomDatabase
import com.mouhsinbourqaiba.sample.roomwordsample.repository.WordRepository
import kotlinx.coroutines.launch

class WordViewModel(application: Application) : AndroidViewModel(application) {

    // The ViewModel maintains a reference to the repository to get data.
    private val repository: WordRepository
    // LiveData gives us updated words when they change.
    val allWords: LiveData<List<Word>>

    init {
        // Gets reference to WordDao from WordRoomDatabase to construct
        val wordsDao = WordRoomDatabase.getDatabase(application , viewModelScope).wordDao()
        repository = WordRepository(wordsDao)
        allWords = repository.allWords
    }

    // The implementation of insert() is completely hidden from the UI.
    fun insert(word: Word) = viewModelScope.launch {
        repository.insert(word)
    }
}