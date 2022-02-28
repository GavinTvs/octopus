package com.gavin.common

import androidx.compose.runtime.Composable
import com.alibaba.android.arouter.facade.template.IProvider

interface MainContainerService: IProvider {
    @Composable
    fun MainContainerComposable()
}