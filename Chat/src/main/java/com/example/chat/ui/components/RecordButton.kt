

package com.example.chat.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.RichTooltipBox
import androidx.compose.material3.RichTooltipState
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.eyram.iconsax.IconSax
import kotlin.math.abs
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecordButton(
    recording: Boolean,
    swipeOffset: () -> Float,
    onSwipeOffsetChange: (Float) -> Unit,
    onStartRecording: () -> Boolean,
    onFinishRecording: () -> Unit,
    onCancelRecording: () -> Unit,
    modifier: Modifier = Modifier
) {
    val transition = updateTransition(targetState = recording, label = "record")
    val scale = transition.animateFloat(
        transitionSpec = { spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessLow) },
        label = "record-scale",
        targetValueByState = { rec -> if (rec) 2f else 1f }
    )
    val containerAlpha = transition.animateFloat(
        transitionSpec = { tween(2000) },
        label = "record-scale",
        targetValueByState = { rec -> if (rec) 1f else 0f }
    )
    val iconColor = transition.animateColor(
        transitionSpec = { tween(200) },
        label = "record-scale",
        targetValueByState = { rec ->
            if (rec) contentColorFor(LocalContentColor.current)
            else LocalContentColor.current
        }
    )

    Box {
        // Background during recording
        Box(
            Modifier
                .matchParentSize()
                .aspectRatio(1f)
                .graphicsLayer {
                    alpha = containerAlpha.value
                    scaleX = scale.value; scaleY = scale.value
                }
                .clip(CircleShape)
                .background(LocalContentColor.current)
        )
        val scope = rememberCoroutineScope()
        val tooltipState = remember { RichTooltipState() }
        RichTooltipBox(
            text = { Text("touch and hold to record") },
            tooltipState = tooltipState
        ) {
            Icon(
                painter = painterResource(IconSax.TwoTone.Microphone2),
                contentDescription = "record message",
                tint = iconColor.value,
                modifier = modifier
                    .sizeIn(minWidth = 56.dp, minHeight = 6.dp)
                    .padding(18.dp)
                    .clickable { }
                    .voiceRecordingGesture(
                        horizontalSwipeProgress = swipeOffset,
                        onSwipeProgressChanged = onSwipeOffsetChange,
                        onClick = { scope.launch { tooltipState.show() } },
                        onStartRecording = onStartRecording,
                        onFinishRecording = onFinishRecording,
                        onCancelRecording = onCancelRecording,
                    )
            )
        }
    }
}

private fun Modifier.voiceRecordingGesture(
    horizontalSwipeProgress: () -> Float,
    onSwipeProgressChanged: (Float) -> Unit,
    onClick: () -> Unit = {},
    onStartRecording: () -> Boolean = { false },
    onFinishRecording: () -> Unit = {},
    onCancelRecording: () -> Unit = {},
    swipeToCancelThreshold: Dp = 200.dp,
    verticalThreshold: Dp = 80.dp,
): Modifier = composed {
    val density = LocalDensity.current
    val swipeToCancelThresholdPx = with(density) { swipeToCancelThreshold.toPx() }
    val verticalThresholdPx = with(density) { verticalThreshold.toPx() }
    var offsetY = 0f
    this
        .pointerInput(Unit) { detectTapGestures { onClick() } }
        .pointerInput(Unit) {
            detectDragGesturesAfterLongPress(
                onDragStart = {
                    onSwipeProgressChanged(0f)
                    offsetY = 0f
                    onStartRecording()
                },
                onDragCancel = { onCancelRecording() },
                onDragEnd = { onFinishRecording() },
                onDrag = { _, dragAmount ->
                    onSwipeProgressChanged(horizontalSwipeProgress() + dragAmount.x)
                    offsetY += dragAmount.y
                    val offsetX = horizontalSwipeProgress()
                    if ((offsetX < 0) &&
                        abs(offsetX) >= swipeToCancelThresholdPx &&
                        abs(offsetY) <= verticalThresholdPx
                    ) {
                        onCancelRecording()
                    }
                }
            )
        }
}
