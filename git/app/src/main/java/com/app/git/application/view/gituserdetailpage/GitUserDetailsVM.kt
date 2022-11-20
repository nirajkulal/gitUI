package com.app.git.application.view.gituserdetailpage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.app.git.application.*
import com.app.git.application.view.GitUserUIModel
import com.app.git.domain.usecases.GitFetchUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitUserDetailsVM @Inject constructor(
    private val fetchUserUseCase: GitFetchUserUseCase,
    savedStateHandle: SavedStateHandle
) : MviViewModel<GitUserDetailState, GitUserDetailEvents, GitUserDetailEffect>(
    GitUserDetailState()
) {

    init {
        savedStateHandle.get<GitUserUIModel>(Constants.USER)?.let {
            setEvent(GitUserDetailEvents.GetUser(it))
            setEvent(GitUserDetailEvents.GetUserFollowers(it.login))
        }
    }

    override suspend fun handleEvents(event: GitUserDetailEvents) {
        when (event) {
            is GitUserDetailEvents.GetUser -> {
                updateState {
                    copy(
                        user = event.user
                    )
                }
            }
            is GitUserDetailEvents.GetUserFollowers -> {
                viewModelScope.launch {
                    event.user?.let {
                        fetchUserUseCase.fetchUserFollowers(it).let {
                            updateState {
                                copy(
                                    followers = it
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override suspend fun handleEffects(effect: GitUserDetailEffect) {}
}

data class GitUserDetailState(
    var user: GitUserUIModel? = null,
    var followers: MutableList<GitUserUIModel>? = null
) : MviState

sealed class GitUserDetailEvents : MviEvent {
    data class GetUser(var user: GitUserUIModel) : GitUserDetailEvents()
    data class GetUserFollowers(var user: String?) : GitUserDetailEvents()
}

sealed class GitUserDetailEffect : MviEffect





