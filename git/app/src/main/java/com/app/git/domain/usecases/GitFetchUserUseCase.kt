package com.app.git.domain.usecases

import com.app.git.application.view.GitUserUIModel
import javax.inject.Inject

class GitFetchUserUseCase @Inject constructor(
    private val repository: GitRepository
) {

    suspend fun fetchUserFollowers(user: String): MutableList<GitUserUIModel> {
        return getUserUIModels(repository.gitUserFollowers(user))
    }

}