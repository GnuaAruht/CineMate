
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection


class DatePickerShape(
    private val cornerShape: Float,
    private val arcShape: Float,
    private val holeRadius: Float
) : Shape {

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val ticketPath = getTicketShapedPath(size = size).apply {
            addPath(getCircleHolePath(size = size))
        }
        return Outline.Generic(ticketPath)
    }

    private fun getCircleHolePath(size: Size): Path {
        return Path().apply {
            addOval(
                Rect(
                    center = Offset(size.width / 2f, size.height * 0.12f + holeRadius),
                    radius = holeRadius
                )
            )
        }
    }

    private fun getTicketShapedPath(size: Size): Path {

        return Path().apply {

            reset()
            moveTo(cornerShape, 0f)

            lineTo(size.width - cornerShape, 0f)
            // top right corner
            arcTo(
                rect = Rect(
                    offset = Offset(x = size.width - cornerShape, y = 0f),
                    size = Size(width = cornerShape, height = cornerShape)
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(size.width, size.height * 0.8f)
            // right arc
            arcTo(
                rect = Rect(
                    center = Offset(
                        size.width,
                        (size.height * 0.72f) + arcShape
                    ),
                    radius = arcShape,
                ),
                startAngleDegrees = 270f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )

            lineTo(size.width, size.height - cornerShape)
            // bottom right corner
            arcTo(
                rect = Rect(
                    offset = Offset(size.width - cornerShape, size.height - cornerShape),
                    size = Size(width = cornerShape, height = cornerShape)
                ),
                startAngleDegrees = 0f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(cornerShape, size.height)
            // bottom left corner
            arcTo(
                rect = Rect(
                    offset = Offset(0f, size.height - cornerShape),
                    size = Size(width = cornerShape, height = cornerShape)
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            lineTo(0f, (size.height * 0.8f) + arcShape * 2)
            // left arch
            arcTo(
                rect = Rect(
                    center = Offset(
                        0f,
                        (size.height * 0.72f) + arcShape
                    ),
                    radius = arcShape,
                ),
                startAngleDegrees = 90f,
                sweepAngleDegrees = -180f,
                forceMoveTo = false
            )

            lineTo(0f, cornerShape)
            // top left corner
            arcTo(
                rect = Rect(
                    offset = Offset.Zero,
                    size = Size(width = cornerShape, height = cornerShape)
                ),
                startAngleDegrees = 180f,
                sweepAngleDegrees = 90f,
                forceMoveTo = false
            )

            close()

        }

    }

}