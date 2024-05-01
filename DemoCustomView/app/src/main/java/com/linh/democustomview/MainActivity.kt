package com.linh.democustomview

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linh.democustomview.ui.theme.DemoCustomViewTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoCustomViewTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var alpha by remember { mutableFloatStateOf(.2f) }
    var borderRadius by remember { mutableFloatStateOf(12f) }
    var blurRadius by remember { mutableFloatStateOf(2f) }
    var offsetY by remember { mutableFloatStateOf(12f) }
    var offsetX by remember { mutableFloatStateOf(12f) }
    var spread by remember { mutableFloatStateOf(2f) }

    Column(modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
            ShadowComponent(
                Color.Black, alpha, borderRadius, blurRadius, offsetY, offsetX, spread
            )
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
        ) {
            ShadowControls(
                alpha = alpha,
                onAlphaChange = { alpha = it },
                borderRadius = borderRadius,
                onBorderRadiusChange = { borderRadius = it },
                blurRadius = blurRadius,
                onBlurRadiusChange = { blurRadius = it },
                offsetY = offsetY,
                onOffsetYChange = { offsetY = it },
                offsetX = offsetX,
                onOffsetXChange = { offsetX = it },
                spread = spread,
                onSpreadChange = { spread = it }
            )
        }
    }
}

@Composable
private fun ShadowControls(
    alpha: Float,
    onAlphaChange: (Float) -> Unit,
    borderRadius: Float,
    onBorderRadiusChange: (Float) -> Unit,
    blurRadius: Float,
    onBlurRadiusChange: (Float) -> Unit,
    offsetY: Float,
    onOffsetYChange: (Float) -> Unit,
    offsetX: Float,
    onOffsetXChange: (Float) -> Unit,
    spread: Float,
    onSpreadChange: (Float) -> Unit
) {
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        ValueSlider(
            title = "Alpha",
            value = alpha,
            onValueChange = onAlphaChange,
            valueRange = 0f..1f,
        )
        ValueSlider(
            title = "Border radius",
            value = borderRadius,
            onValueChange = onBorderRadiusChange,
            valueRange = 0f..48f,
        )
        ValueSlider(
            title = "Blur radius",
            value = blurRadius,
            onValueChange = onBlurRadiusChange,
            valueRange = 0f..15f,
        )
        ValueSlider(
            title = "offsetY",
            value = offsetY,
            onValueChange = onOffsetYChange,
            valueRange = -48f..48f,
        )
        ValueSlider(
            title = "offsetX",
            value = offsetX,
            onValueChange = onOffsetXChange,
            valueRange = -48f..48f,
        )
        ValueSlider(
            title = "Spread",
            value = spread,
            onValueChange = onSpreadChange,
            valueRange = 0f..15f,
        )
    }
}

@Composable
private fun ValueSlider(
    title: String,
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, fontSize = 14.sp)
        Slider(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            valueRange = valueRange,
        )
    }
}
