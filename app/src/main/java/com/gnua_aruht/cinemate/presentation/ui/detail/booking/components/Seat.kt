package com.gnua_aruht.cinemate.presentation.ui.detail.booking.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme

@Composable
fun Seat(
    modifier : Modifier = Modifier,
    color: Color = Color.White.copy(alpha = 0.6f),
) {

    Spacer(modifier = modifier
        .drawWithCache {

            val seatPath = Path().apply {
                addRoundRect(
                    roundRect = RoundRect(
                        rect = Rect(
                            offset = Offset.Zero,
                            size = Size(width = size.width, height = size.height)
                        ),
                        cornerRadius = CornerRadius(x = 24f, y = 24f)
                    )
                )
            }

            val linePath = Path().apply {
                reset()
                moveTo(0f, size.height * 0.1f)
                lineTo(size.width * 0.2f, size.height * 0.2f)
                lineTo(size.width * 0.2f, size.height * 0.8f)
                lineTo(0f, size.height)
                moveTo(size.width * 0.2f, size.height * 0.8f)
                lineTo(size.width * 0.8f, size.height * 0.8f)
                lineTo(size.width, size.height)
                moveTo(size.width * 0.8f, size.height * 0.8f)
                lineTo(size.width * 0.8f, size.height * 0.2f)
                lineTo(size.width, size.height * 0.1f)
            }

            onDrawBehind {
                drawPath(seatPath, color = color)
                clipPath(seatPath) {
                    drawPath(linePath, color = Color.Black, style = Stroke(width = 3.2f))
                }
            }

        }
    )

}

@Preview
@Composable
private fun SeatPreview() {
    CineMateTheme {
        Seat(modifier = Modifier.size(36.dp))
    }
}