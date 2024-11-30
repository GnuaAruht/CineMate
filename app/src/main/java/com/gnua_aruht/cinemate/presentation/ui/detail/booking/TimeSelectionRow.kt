
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun TimeSelectionRow(
    timeListString : List<String>,
    selectedTime : String,
    onTimeSelected : (String) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding : PaddingValues = PaddingValues(0.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(12.dp)
) {

    val animatedValues = List(timeListString.size) { remember { Animatable(0f) } }

    LaunchedEffect(Unit) {
        animatedValues.forEachIndexed { index, animation ->
            animation.animateTo(
                targetValue = 1f,
                animationSpec = tween(durationMillis = 60, delayMillis = index * 10)
            )
        }
    }

    LazyRow(
        modifier = modifier.fillMaxWidth().wrapContentWidth(),
        contentPadding = contentPadding,
        horizontalArrangement = horizontalArrangement
    ) {
        items(timeListString.size) { index ->
            val time = timeListString[index]
            TimeItem(
                timeString = time,
                isSelected = time == selectedTime,
                modifier = modifier
                    .graphicsLayer {
                        alpha = animatedValues[index].value
                        scaleX = animatedValues[index].value
                        scaleY = animatedValues[index].value
                    }
                    .clickable {
                    onTimeSelected(time)
                }
            )
        }
    }

}

@Composable
fun TimeItem(
    timeString : String,
    isSelected : Boolean,
    modifier: Modifier = Modifier
) {

    Text(
        text = timeString,
        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
        textAlign = TextAlign.Center,
        color = if(isSelected) Color.Black else Color.White,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if(isSelected) MaterialTheme.colorScheme.primary else Color.White.copy(alpha = 0.4f))
            .padding(horizontal = 20.dp, vertical = 10.dp)
    )
}
