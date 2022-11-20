package com.app.git.data.network.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/search/users")
    suspend fun gitSearch(
        @Header("Authorization") authorization: String = "", @Query("q") query: String
    ): Response<UsersResponse>

    @GET("/users/{user}/followers")
    suspend fun gitUserFollowers(
        @Header("Authorization") authorization: String = "", @Path("user") user: String
    ): Response<ArrayList<User>>
}