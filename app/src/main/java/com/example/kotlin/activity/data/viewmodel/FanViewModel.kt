package com.example.kotlin.activity.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.dataclass.Fan
import com.example.kotlin.activity.data.repository.FanRepo

class FanViewModel() : ViewModel() {
    // init
    var Fan = MutableLiveData<Fan>()

    private val FanRepo: FanRepo by lazy { FanRepo() }


    fun getFan() {
        // subscribe
        FanRepo.getdata().subscribe(
            {
                Fan.value = it
            },
            {
                // error
            },
        )

    }

}