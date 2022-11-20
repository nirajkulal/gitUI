package com.app.git.application.view.gituserdetailpage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.git.application.view.GitUserUIModel
import com.app.git.application.view.GitUsers
import com.app.git.application.view.typography
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GitUserDetailsActivity : ComponentActivity() {

    private val viewModel: GitUserDetailsVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitUserDetailsPage(
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun GitUserDetailsPage(viewModel: GitUserDetailsVM) {
    val state = viewModel.state.collectAsState().value
    Surface {
        Column {
            GitUserHeader(state.user)
            Divider(thickness = 1.dp)
            GitUserFollower(state.followers)
        }
    }
}

@Composable
fun GitUserFollower(followers: MutableList<GitUserUIModel>?) {
    followers?.let {
        Text(
            modifier = Modifier.padding(32.dp),
            text = stringResource(id = com.app.git.R.string.followers),
            style = typography.subtitle2
        )
        GitUsers(it)
    }
}

@Composable
fun GitUserHeader(user: GitUserUIModel?) {
    user?.let {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {
            AsyncImage(
                model = it.avatar,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .width(150.dp)
                    .height(150.dp)
            )
            Text(
                modifier = Modifier.padding(32.dp),
                text = it.login ?: "",
                style = typography.subtitle1
            )
        }
    }
}
