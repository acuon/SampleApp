package com.acuon.sampleapp.ui.favorite.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acuon.sampleapp.common.BaseViewModel
import com.acuon.sampleapp.common.ResultOf
import com.acuon.sampleapp.domain.model.UserItem
import com.acuon.sampleapp.domain.repository.IHomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val repository: IHomeRepository) :
    BaseViewModel() {

    private val _users = MutableLiveData<ResultOf<List<UserItem?>?>>()
    val users: LiveData<ResultOf<List<UserItem?>?>>
        get() = _users

    init {
        getFavoriteUsers()
    }

    private fun getFavoriteUsers() {
        execute {
            _users.value = ResultOf.Loading()
            kotlin.runCatching {
                repository.getFavoriteUsers()
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