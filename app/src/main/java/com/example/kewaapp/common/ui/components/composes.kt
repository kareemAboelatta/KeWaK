package com.example.kewaapp.common.ui.components

import android.content.res.Configuration
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kewaapp.R

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun GradientProgressbarPreview() {
    GradientProgressbar()
}

@Composable
fun GradientProgressbar(
    modifier: Modifier = Modifier,
    percentage: Float = 90F,
    indicatorHeight: Dp = 40.dp,
    backgroundIndicatorColor: Color = Color.LightGray.copy(alpha = 0.3f),
    gradientColors: List<Color> = listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
    ),
    animationDuration: Int = 500,
    animationDelay: Int = 0
) {


    val animateNumber = animateFloatAsState(
        targetValue = percentage,
        animationSpec = tween(
            durationMillis = animationDuration,
            delayMillis = animationDelay
        ), label = ""
    )

    Box(
        Modifier
            .fillMaxWidth()
            .border(
                border = BorderStroke(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.surface,
                ),
                shape = CircleShape
            )


    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(
                modifier = Modifier
                    .weight(1F)
                    .padding(horizontal = 20.dp)
                    .height(height = indicatorHeight)
            ) {

                // Background indicator
                drawLine(
                    color = backgroundIndicatorColor,
                    cap = StrokeCap.Round,
                    strokeWidth = size.height,
                    start = Offset(x = 0f, y = size.height / 2),
                    end = Offset(x = size.width, y = size.height / 2)
                )

                // Convert the downloaded percentage into progress (width of foreground indicator)
                val progress =
                    (animateNumber.value / 100) * size.width // size.width returns the width of the canvas

                // Foreground indicator
                drawLine(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    ),
                    cap = StrokeCap.Round,
                    strokeWidth = size.height,
                    start = Offset(x = 0f, y = size.height / 2),
                    end = Offset(x = progress, y = size.height / 2)
                )

            }

        }
        Icon(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .align(Alignment.CenterEnd),
            painter = painterResource(
                id = R.drawable.ic_alarm
            ), contentDescription ="Alarm",
            tint = MaterialTheme.colorScheme.surface
        )

    }


}
