package com.app.git.domain.usecases

import com.app.git.application.view.GitUserUIModel
import com.app.git.data.network.retrofit.User

fun getUserUIModels(users: ArrayList<User>?): MutableList<GitUserUIModel> {
    val userUiModel = mutableListOf<GitUserUIModel>()
    users?.forEach {
        userUiModel.add(
            GitUserUIModel(
                avatar = it.avatarUrl,
                login = it.login
            )
        )
    }
    return userUiModel
}