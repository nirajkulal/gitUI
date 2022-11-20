package com.app.git.application.view.gitsearchpage

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.app.git.application.Constants
import com.app.git.application.view.GitUserUIModel
import com.app.git.application.view.GitUsers
import com.app.git.application.view.typography
import com.app.git.application.view.gituserdetailpage.GitUserDetailsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitSearchActivity : ComponentActivity() {

    private val viewModel: GitSearchViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GitSearchPage(
                viewModel = viewModel
            ) {
                launchDetailPage(it)
            }
        }
    }

    private fun launchDetailPage(user: GitUserUIModel?) {
        startActivity(Intent(this, GitUserDetailsActivity::class.java).apply {
            putExtra(Constants.USER, user)
        })
    }
}

@Composable
private fun GitSearchPage(viewModel: GitSearchViewModel, onClick: (user: GitUserUIModel?) -> Unit) {
    val state = viewModel.state.collectAsState().value
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            GitSearchInputText {
                viewModel.setEvent(
                    GitSearchViewEvents.OnSearch(it)
                )
            }
            GitUsers(state.users, onClick)
        }
    }
}

@Composable
fun GitSearchInputText(
    onSearch: (query: String) -> Unit
) {
    var text by remember {
        mutableStateOf("")
    }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = stringResource(id = com.app.git.R.string.search),
            )
        },
        textStyle = typography.caption,
        value = text,
        onValueChange = {
            text = it
            onSearch(it)
        })
}
