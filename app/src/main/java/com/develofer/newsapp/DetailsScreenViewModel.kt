package com.develofer.newsapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.develofer.newsapp.model.New
import com.develofer.newsapp.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val repository: NewsRepository
): ViewModel() {

    private val _new = MutableLiveData<New>()

    fun getNewsByTitle(title: String) : LiveData<New> {
        viewModelScope.launch(Dispatchers.IO) {
            val news = repository.getNew(title)
            _new.postValue(news)
        }
        return _new
    }

}