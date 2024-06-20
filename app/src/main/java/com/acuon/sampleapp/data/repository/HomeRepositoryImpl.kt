package com.acuon.sampleapp.data.repository

import com.acuon.sampleapp.common.ResultOf
import com.acuon.sampleapp.data.local.UsersDao
import com.acuon.sampleapp.data.remote.ApiClass
import com.acuon.sampleapp.data.remote.dto.toUserItem
import com.acuon.sampleapp.domain.model.UserItem
import com.acuon.sampleapp.domain.repository.IHomeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import java.io.IOException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiClass,
    private val usersDao: UsersDao
) : IHomeRepository {

    override suspend fun getUsersFromNetwork(): ResultOf<List<UserItem?>?> {
        return try {
            ResultOf.Loading(true)
            val response = apiService.getUsers()?.map { it?.toUserItem() }
            coroutineScope {
                async(Dispatchers.IO) {
                    addToDatabase(response ?: listOf())
                }.await()
            }
            ResultOf.Success(response)
        } catch (e: Exception) {
            ResultOf.Error(e.localizedMessage ?: "something went wrong", e)
        } catch (e: IOException) {
            ResultOf.Error("please connect to internet", e)
        }
    }

    override suspend fun getFavoriteUsers(): Flow<List<UserItem?>?> {
        return usersDao.getFavoriteUsers()
    }

    override suspend fun searchQuery(query: String): Flow<List<UserItem?>?> {
        return usersDao.searchUsersByName(query)
    }

    override fun addToDatabase(users: List<UserItem?>) {
        usersDao.insertUsers(users.map { it ?: UserItem() })
    }

    override fun addToFavorite(id: Int, favorite: Boolean) {
        usersDao.addToFavorite(id, favorite)
    }

    override fun updateUser(user: UserItem) {
        usersDao.updateUser(user)
    }
}