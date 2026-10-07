package com.example.getar.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getar.ui.theme.CoralPinkDark
import com.example.getar.ui.theme.CoralPinkLight
import com.example.getar.ui.theme.DeepTealDark
import com.example.getar.ui.theme.DeepTealLight
import com.example.getar.ui.theme.InkText
import com.example.getar.ui.theme.TextPale

@Composable
fun LoadingView(modifier: Modifier = Modifier) {
    val isDark = isSystemInDarkTheme()
    val progressColor = if (isDark) CoralPinkDark else CoralPinkLight

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(56.dp),
            color = progressColor,
            strokeWidth = 5.dp
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Memuat data gempa BMKG...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ErrorView(
    message: String,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    val errorCircleColor = if (isDark) CoralPinkDark else CoralPinkLight
    val buttonBgColor = if (isDark) TextPale else InkText
    val buttonTextColor = if (isDark) InkText else Color.White

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .background(color = errorCircleColor, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Peringatan Error",
                tint = Color.White,
                modifier = Modifier.size(48.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Gagal memuat data",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = onRetry,
            shape = RoundedCornerShape(9999.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonBgColor,
                contentColor = buttonTextColor
            ),
            modifier = Modifier
                .fillMaxWidth(0.65f)
                .height(48.dp)
        ) {
            Text(
                text = "Coba lagi",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun EmptyView(
    query: String,
    modifier: Modifier = Modifier
) {
    val isDark = isSystemInDarkTheme()
    val tealColor = if (isDark) DeepTealDark else DeepTealLight

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(
                    color = tealColor.copy(alpha = 0.2f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "🔍",
                fontSize = 36.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Tidak ada hasil",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Tidak ditemukan gempa untuk wilayah \"$query\". Silakan coba kata kunci lain.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SeismographMotif(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Canvas(modifier = modifier.size(width = 36.dp, height = 20.dp)) {
        val path = Path().apply {
            moveTo(0f, size.height * 0.5f)
            lineTo(size.width * 0.25f, size.height * 0.5f)
            lineTo(size.width * 0.35f, size.height * 0.1f)
            lineTo(size.width * 0.5f, size.height * 0.95f)
            lineTo(size.width * 0.65f, size.height * 0.2f)
            lineTo(size.width * 0.75f, size.height * 0.5f)
            lineTo(size.width, size.height * 0.5f)
        }
        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = 3.dp.toPx(),
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}
