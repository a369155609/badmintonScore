package com.junyu.badmintonscore.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * @Name MainViewModel
 * @Descript TODO
 * @CreateTime 2023/5/11 15:02
 * @Created by Administrator
 */
@HiltViewModel
class MainViewModel @Inject constructor() :ViewModel() {

    val testLIst = listOf("1","1","1","1","1")

    private val _redCount = MutableLiveData<Int>(0)

    private val _blueCount = MutableLiveData<Int>(0)

    val redCount:LiveData<Int> get() = _redCount
    val blueCount:LiveData<Int> get() = _blueCount

    fun addRedCount(){
        _redCount.value = _redCount.value?.plus(1)
    }

    fun addRedCount(value:Int){
        _redCount.value = _redCount.value?.plus(value)
    }

    fun desRedCount(){
        _redCount.value = _redCount.value?.minus(1)
    }

    fun addBlueCount(){
        _blueCount.value = _blueCount.value?.plus(1)
    }

    fun addBlueCount(value:Int){
        _blueCount.value = _blueCount.value?.plus(value)
    }

    fun desBlueCount(){
        _blueCount.value = _blueCount.value?.minus(1)
    }


}