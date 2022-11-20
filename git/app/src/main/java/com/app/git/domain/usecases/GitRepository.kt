package com.app.git.domain.usecases

import com.app.git.data.network.retrofit.User

interface GitRepository {
    suspend fun gitSearch(query: String): ArrayList<User>?
    suspend fun gitUserFollowers(user: String): ArrayList<User>?
}