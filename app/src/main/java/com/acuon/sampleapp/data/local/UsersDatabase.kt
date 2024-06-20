package com.acuon.sampleapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acuon.sampleapp.domain.model.UserItem

/**
 * RoomDatabase class for storing Users data
 * @param entities - array of entity tables to be used in database
 * @param version - version of the database schema
 * @param exportSchema - to export the schema to a file, Default is false
 */
@Database(entities = [UserItem::class], version = 1, exportSchema = false)
abstract class UsersDatabase : RoomDatabase() {
    /**
     * return UsersDao object
     */
    abstract fun getUsersDao(): UsersDao

}