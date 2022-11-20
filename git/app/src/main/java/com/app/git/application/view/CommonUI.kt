package com.app.git.application.view

import android.os.Parcelable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.app.git.R
import kotlinx.parcelize.Parcelize


val typography = androidx.compose.material.Typography()

@Composable
fun GitUsers(users: MutableList<GitUserUIModel>, onClick: (user: GitUserUIModel?) -> Unit = {}) {
    when (users.size) {
        0 -> {
            Text(
                modifier = Modifier
                    .padding(32.dp),
                style = typography.overline,
                text = stringResource(id = R.string.empty),
            )
        }
        else -> {
            LazyColumn {
                itemsIndexed(items = users) { index, item ->
                    GitUser(item, onClick)
                }
            }
        }
    }
}

@Composable
fun GitUser(item: GitUserUIModel, onClick: (user: GitUserUIModel?) -> Unit) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .clickable {
                onClick(item)
            }
    ) {
        AsyncImage(
            model = item.avatar,
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .width(80.dp)
                .height(80.dp)
        )
        Text(
            modifier = Modifier.padding(16.dp),
            text = item.login ?: "",
            style = typography.body1
        )
    }
}

@Parcelize
data class GitUserUIModel(
    var avatar: String?,
    var login: String?
) : Parcelable

