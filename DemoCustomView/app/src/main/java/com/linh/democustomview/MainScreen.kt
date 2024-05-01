package com.linh.democustomview

import android.health.connect.datatypes.HeightRecord
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.linh.democustomview.custom.ShadowContainer
import com.linh.democustomview.custom.shadowView

@Composable
fun ShadowComponent(
    width: Dp,
    height: Dp,
    color: Color,
    alpha: Float,
    borderRadius: Float,
    blurRadius: Float,
    offsetY: Float,
    offsetX: Float,
    spread: Float
) {

    ShadowContainer {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .size(width, height)
                    .padding(horizontal = 24.dp, vertical = 24.dp)
                    .shadowView(
                        color,
                        borderRadius.dp,
                        blurRadius.dp,
                        offsetY.dp,
                        offsetX.dp,
                        spread.dp,
                        alpha
                    )
                    .clip(RoundedCornerShape(borderRadius.dp))
                    .background(MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "ABC")
            }
        }
    }
}
