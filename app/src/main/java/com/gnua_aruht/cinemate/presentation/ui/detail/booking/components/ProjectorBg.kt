package com.gnua_aruht.cinemate.presentation.ui.detail.booking.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme


@Composable
fun ProjectorBg(modifier: Modifier = Modifier, ) {

    val textMeasurer = rememberTextMeasurer()

    Box(
        modifier = modifier
            .drawWithCache {

                val projectorPath = Path().apply {
                    reset()
                    moveTo(size.width * 0.14f, size.height * 0.34f)
                    quadraticTo(
                        x1 = size.width / 2,
                        y1 = 0f,
                        x2 = size.width * 0.86f,
                        y2 = size.height * 0.34f
                    )
                }

                val gradientPath = Path().apply {
                    reset()
                    moveTo(size.width * 0.14f, size.height * 0.34f)
                    lineTo(0f, size.height)
                    lineTo(size.width, size.height)
                    lineTo(size.width * 0.86f, size.height * 0.34f)
                    quadraticTo(
                        x1 = size.width / 2,
                        y1 = 0f,
                        x2 = size.width * 0.14f,
                        y2 = size.height * 0.34f
                    )
                    close()
                }

                val linearGradient = Brush.verticalGradient(
                    colorStops = arrayOf(
                        0.4f to Color.White.copy(alpha = 0.08f),
                        0.8f to Color.Transparent
                    )
                )

                val measuredText = textMeasurer.measure(
                    AnnotatedString("Screen"),
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                )

                onDrawBehind {
                    drawPath(
                        projectorPath,
                        color = Color.White,
                        style = Stroke(width = 4.8f, cap = StrokeCap.Round)
                    )
                    drawPath(path = gradientPath, brush = linearGradient)
                    drawText(
                        measuredText,
                        topLeft = Offset(
                            x = (size.width / 2) - (measuredText.size.width / 2),
                            y = size.height * 0.34f
                        )
                    )
                }
            }
    )

}


@Preview
@Composable
private fun ProjectorBgPreview() {
    CineMateTheme {
        ProjectorBg(modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3.2f)
        )
    }
}