package com.gnua_aruht.cinemate.presentation.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.R
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeAppBar(
    onMenuClicked: () -> Unit,
    onSearchClicked : () -> Unit,
    onNotificationClicked : () -> Unit,
    onAvatarClicked : () -> Unit,
    modifier: Modifier = Modifier,
) {

    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {},
        navigationIcon = {
            IconButton(onClick = onMenuClicked) {
                Icon(
                    imageVector = Icons.Rounded.Menu,
                    contentDescription = null,
                )
            }
        },
        actions = {
            IconButton(onClick = onSearchClicked) {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                )
            }
            IconButton(onClick = onNotificationClicked) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))
            Image(
                painter = painterResource(R.drawable.user_profile),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(34.dp)
                    .background(Color.White)
                    .clickable { onAvatarClicked() }
            )
            Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_medium)))
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
    )

}

@Preview
@Composable
private fun HomeAppBarPreview() {
    CineMateTheme {
        HomeAppBar(
            onMenuClicked = {},
            onNotificationClicked = {},
            onSearchClicked = {},
            onAvatarClicked = {}
        )
    }
}