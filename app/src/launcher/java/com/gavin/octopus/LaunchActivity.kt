package com.gavin.octopus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.gavin.octopus.ui.theme.OctopusTheme
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter
import com.gavin.common.MainContainerService
import com.gavin.common.constant.Constants

class LaunchActivity : ComponentActivity() {
    @Autowired(name = Constants.ARouterPath.MAIN_CONTAINER)
    lateinit var mainContainerService: MainContainerService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        setContent {
            OctopusTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    mainContainerService.MainContainerComposable()
                }
            }
        }
    }
}

