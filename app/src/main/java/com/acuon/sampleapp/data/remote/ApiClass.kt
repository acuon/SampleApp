package com.acuon.sampleapp.data.remote

import com.acuon.sampleapp.data.remote.dto.UsersResponseDTOItem
import retrofit2.http.GET

interface ApiClass {

    @GET("users")
    suspend fun getUsers(): List<UsersResponseDTOItem?>?

}