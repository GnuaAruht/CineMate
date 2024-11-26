
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gnua_aruht.cinemate.R
import com.gnua_aruht.cinemate.presentation.theme.CineMateTheme

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        modifier = modifier.heightIn(min = 56.dp),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Preview
@Composable
private fun AppButtonPreview() {
    CineMateTheme {
        AppButton(
            text = stringResource(R.string.get_started),
            onClick = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}