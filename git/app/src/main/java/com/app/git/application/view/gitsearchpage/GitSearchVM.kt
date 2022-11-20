package com.app.git.application.view.gitsearchpage

import androidx.lifecycle.viewModelScope
import com.app.git.application.MviEffect
import com.app.git.application.MviEvent
import com.app.git.application.MviState
import com.app.git.application.MviViewModel
import com.app.git.application.view.GitUserUIModel
import com.app.git.domain.usecases.GitSearchUsersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GitSearchViewModel @Inject constructor(
    private val searchUseCase: GitSearchUsersUseCase
) :
    MviViewModel<GitSearchViewState, GitSearchViewEvents, GitSearchViewEffect>(
        GitSearchViewState()
    ) {

    var job: Job? = null

    override suspend fun handleEvents(event: GitSearchViewEvents) {
        when (event) {
            is GitSearchViewEvents.OnSearch -> {
                /**
                 * Remove old request
                 */
                job?.let {
                    if (it.isActive)
                        it.cancel()
                }
                job = viewModelScope.launch {
                    /**
                     * Debounce of 0.5sec added
                     */
                    delay(500)
                    searchUseCase.searchUser(event.query).let {
                        updateState {
                            copy(
                                users = it
                            )
                        }
                    }
                }
            }
        }
    }

    override suspend fun handleEffects(effect: GitSearchViewEffect) {}
}

data class GitSearchViewState(
    var query: String = "",
    var users: MutableList<GitUserUIModel> = mutableListOf()
) : MviState

sealed class GitSearchViewEvents : MviEvent {
    data class OnSearch(
        var query: String
    ) : GitSearchViewEvents()
}

sealed class GitSearchViewEffect : MviEffect

