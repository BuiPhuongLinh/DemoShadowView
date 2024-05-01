package com.linh.democustomview.custom

import android.graphics.BlurMaskFilter
import android.os.Build
import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ShadowContainer(content: @Composable () -> Unit) {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
        AndroidView(
            factory = { context ->
                ComposeView(context).apply {
                    setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                    setContent(content)
                }
            },
            modifier = Modifier.fillMaxSize()
        )
    } else {
        content()
    }
}

fun Modifier.shadowView(
    color: Color,
    borderRadius: Dp,
    blurRadius: Dp,
    offsetY: Dp,
    offsetX: Dp,
    spread: Dp,
    alpha: Float = 0.2f
) = this.then(
    Modifier.drawBehind {
        drawIntoCanvas {
            val paint = Paint()
            val frameworkPaint = paint.asFrameworkPaint()
            val spreadPixel = spread.toPx()
            val startPixel = (0f - spreadPixel) + (offsetX.toPx())
            val topPixel = (0f - spreadPixel) + (offsetY.toPx())
            val endPixel = (this.size.width + spreadPixel) + offsetX.toPx()
            val bottomPixel = (this.size.height + spreadPixel) + offsetY.toPx()

            if (blurRadius != 0.dp) {
                frameworkPaint.maskFilter =
                    (BlurMaskFilter(blurRadius.toPx(), BlurMaskFilter.Blur.NORMAL))
            }

            frameworkPaint.color = color.copy(alpha = alpha).toArgb()
            it.drawRoundRect(
                left = startPixel,
                top = topPixel,
                right = endPixel,
                bottom = bottomPixel,
                radiusX = borderRadius.toPx(),
                radiusY = borderRadius.toPx(),
                paint
            )
        }
    }
)
