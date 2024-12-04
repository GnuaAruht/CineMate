package com.gnua_aruht.cinemate.presentation.ui.detail.booking.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SeatLayout(
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit
) {

    Box(modifier = modifier) {
        ProjectorBg(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 120.dp, max = 140.dp)
        )
        content(PaddingValues(top = 88.dp))
    }

}
