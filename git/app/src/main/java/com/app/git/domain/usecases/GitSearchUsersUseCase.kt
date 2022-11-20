package com.app.git.domain.usecases

import com.app.git.application.view.GitUserUIModel
import javax.inject.Inject

class GitSearchUsersUseCase @Inject constructor(
    private val repository: GitRepository
) {
    suspend fun searchUser(query: String): MutableList<GitUserUIModel> {
        return getUserUIModels(repository.gitSearch(query))
    }
}