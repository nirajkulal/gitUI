package com.app.git.data.repository

import com.app.git.data.network.GitNetwork
import com.app.git.data.network.retrofit.User
import com.app.git.domain.usecases.GitRepository
import javax.inject.Inject

class GitRepositoryImp @Inject constructor(
    val network: GitNetwork
) : GitRepository {

    override suspend fun gitSearch(query: String): ArrayList<User>? =
        network.gitSearch(query)


    override suspend fun gitUserFollowers(user: String): ArrayList<User>? =
        network.gitUserFollowers(user)

}