package com.acuon.sampleapp.ui.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acuon.sampleapp.common.BaseViewModel
import com.acuon.sampleapp.common.ResultOf
import com.acuon.sampleapp.domain.model.UserItem
import com.acuon.sampleapp.domain.repository.IHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: IHomeRepository) : BaseViewModel() {

    init {
        getUsers()
    }

    private val _users = MutableLiveData<ResultOf<List<UserItem?>?>>()
    val users: LiveData<ResultOf<List<UserItem?>?>>
        get() = _users

    private fun getUsers() {
        execute {
            _users.value = ResultOf.Loading()
            kotlin.runCatching {
                repository.getUsersFromNetwork()
            }.onSuccess {
                _users.value = it
            }.onFailure {
                _users.value = ResultOf.Error(it.localizedMessage, it)
            }
        }
    }

    private var searchJob: Job? = null

    fun searchQuery(query: String) {
        searchJob?.cancel()
        searchJob = execute {
            kotlin.runCatching {
                repository.searchQuery(query)
            }.onSuccess {
                it.collect { list ->
                    _users.value = ResultOf.Success(list)
                }
            }.onFailure {
                _users.value = ResultOf.Error(it.localizedMessage, it)
            }
        }
    }

    fun addToFavorite(id: Int, favorite: Boolean) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.addToFavorite(id, favorite)
            }
        }
    }

}