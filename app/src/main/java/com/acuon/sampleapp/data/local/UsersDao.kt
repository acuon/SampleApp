package com.acuon.sampleapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.acuon.sampleapp.domain.model.UserItem
import kotlinx.coroutines.flow.Flow

/**
 * Dao interface for Users database
 */
@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(movies: List<UserItem>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(movie: UserItem)

    @Delete
    fun removeUser(movie: UserItem)

    @Query("DELETE FROM Users WHERE id = :id")
    fun deleteFromDatabase(id: Long)

    @Query("SELECT * FROM Users WHERE id = :id")
    fun getUserById(id: Long): UserItem?

    @Query("SELECT * FROM Users")
    fun getUsers(): Flow<List<UserItem?>?>

    @Query("SELECT * FROM Users WHERE isFavorite == 1")
    fun getFavoriteUsers(): Flow<List<UserItem?>?>

    @Query("SELECT * FROM Users WHERE name LIKE '%' || :name || '%'")
    fun searchUsersByName(name: String): Flow<List<UserItem?>?>

    @Query("UPDATE Users SET isFavorite = :favorite WHERE id = :id")
    fun addToFavorite(id: Int, favorite: Boolean)

    @Update
    fun updateUser(user: UserItem): Int
}