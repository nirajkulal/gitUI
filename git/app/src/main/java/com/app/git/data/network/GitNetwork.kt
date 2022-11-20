package com.app.git.data.network

import com.app.git.data.network.retrofit.RetrofitInstance
import com.app.git.data.network.retrofit.User
import javax.inject.Inject

class GitNetwork @Inject constructor(
    private val retrofitInstance: RetrofitInstance
) {
    suspend fun gitSearch(query: String): ArrayList<User>? =
        retrofitInstance.getRetrofit().gitSearch(query = query).body()?.items


    suspend fun gitUserFollowers(user: String): ArrayList<User>? =
        retrofitInstance.getRetrofit().gitUserFollowers(user = user).body()
}