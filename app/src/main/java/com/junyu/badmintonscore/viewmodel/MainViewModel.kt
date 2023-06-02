package com.junyu.badmintonscore.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

/**
 * @Name MainViewModel
 * @Descript TODO
 * @CreateTime 2023/5/11 15:02
 * @Created by Administrator
 */
@HiltViewModel
class MainViewModel:ViewModel() {

    val testLIst = listOf("1","1","1","1","1")

}