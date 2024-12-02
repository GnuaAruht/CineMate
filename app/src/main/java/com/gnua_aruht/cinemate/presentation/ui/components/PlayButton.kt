
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun PlayButton(modifier: Modifier = Modifier) {

    Surface(
        modifier = modifier,
        color = Color.White.copy(alpha = 0.48f),
        shape = CircleShape
    ) {
        Icon(
            Icons.Rounded.PlayArrow,
            tint = Color.White,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }

}