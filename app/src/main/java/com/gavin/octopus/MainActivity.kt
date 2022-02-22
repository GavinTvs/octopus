package com.gavin.octopus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.gavin.demo.NestedScrollDemoScreen
import com.gavin.octopus.ui.theme.OctopusTheme
import com.gavin.video_detail.VideoDetailScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OctopusTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
//    NestedScrollDemoScreen()
    VideoDetailScreen()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    OctopusTheme {
        Greeting()
    }
}