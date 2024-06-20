package com.acuon.sampleapp.domain.repository

import com.acuon.sampleapp.common.ResultOf
import com.acuon.sampleapp.domain.model.UserItem
import kotlinx.coroutines.flow.Flow

interface IHomeRepository {

    suspend fun getUsersFromNetwork(): ResultOf<List<UserItem?>?>

    suspend fun getFavoriteUsers(): Flow<List<UserItem?>?>

    suspend fun searchQuery(query: String): Flow<List<UserItem?>?>

    fun addToDatabase(users: List<UserItem?>)

    fun addToFavorite(id: Int, favorite: Boolean)

    fun updateUser(user: UserItem)

}