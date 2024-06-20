package com.acuon.sampleapp.domain.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Users")
@Parcelize
data class UserItem(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null,
    @ColumnInfo(name = "email")
    val email: String? = null,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name = "phone")
    val phone: String? = null,
    @ColumnInfo(name = "username")
    val username: String? = null,
    @ColumnInfo(name = "website")
    val website: String? = null,
    @ColumnInfo(name = "companyBs")
    val companyBs: String? = null,
    @ColumnInfo(name = "companyCatchPhrase")
    val companyCatchPhrase: String? = null,
    @ColumnInfo(name = "companyName")
    val companyName: String? = null,
    @ColumnInfo(name = "city")
    val city: String? = null,
    @ColumnInfo(name = "latitude")
    val latitude: String? = null,
    @ColumnInfo(name = "longitude")
    val longitude: String? = null,
    @ColumnInfo(name = "street")
    val street: String? = null,
    @ColumnInfo(name = "suite")
    val suite: String? = null,
    @ColumnInfo(name = "zipcode")
    val zipcode: String? = null,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
) : Parcelable