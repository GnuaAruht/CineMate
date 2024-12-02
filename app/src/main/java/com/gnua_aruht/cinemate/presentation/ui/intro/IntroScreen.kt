package com.gnua_aruht.cinemate.presentation.ui.intro

import AppButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gnua_aruht.cinemate.R
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme
import com.gnua_aruht.cinemate.presentation.theme.backgroundColor

@Composable
fun IntroScreen(
    onGetStarted: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Image(
            painter = painterResource(id = R.drawable.bg_intro),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.72f)
                .drawWithContent {
                    drawContent()
                    drawRect(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.1f to Color.Transparent,
                                0.84f to backgroundColor
                            )
                        )
                    )
                },
            contentScale = ContentScale.Crop
        )

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent,
            content = { paddingValues ->
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(all = dimensionResource(R.dimen.default_padding))
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.54f))
                    Text(
                        text = buildAnnotatedString {
                            append("Cine")
                            withStyle(SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                                append("Mate")
                            }
                        },
                        color = Color.White,
                        style = TextStyle(
                            fontSize = 42.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = stringResource(R.string.intro_text),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
                    )
                    Text(
                        text = stringResource(R.string.intro_description),
                        color = MaterialTheme.colorScheme.onSurface,
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Normal
                        ),
                        modifier = Modifier.widthIn(max = 688.dp)
                    )
                }
            },
            bottomBar = {
                BottomAppBar(
                    modifier = Modifier.fillMaxWidth(),
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    AppButton(
                        text = stringResource(R.string.get_started),
                        onClick = onGetStarted,
                        modifier = Modifier
                            .weight(1f)
                            .wrapContentWidth()
                            .fillMaxWidth(fraction = 0.76f)
                    )
                }
            }
        )
    }
}

@Preview
@Composable
private fun IntroScreenPreview() {
    CineMateTheme {
        IntroScreen(onGetStarted = {})
    }
}