package com.gavin.octopus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import com.alibaba.android.arouter.launcher.ARouter
import com.gavin.octopus.ui.theme.OctopusTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OctopusTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Text(text = "Hello")
                }
            }
        }
    }
}

