package com.example.kotlin.activity.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.dataclass.ValueHistory
import com.example.kotlin.activity.data.repository.ValueHistoryRepo


class ValueHistoryViewModel() : ViewModel() {
    // init
    var ValueHistory = MutableLiveData<ValueHistory>()

    private val ValueHistoryRepo: ValueHistoryRepo by lazy { ValueHistoryRepo() }

    fun getPersonalCoin() {
        // subscribe
        ValueHistoryRepo.getdata().subscribe(
            {
                ValueHistory.value = it
            },
            {
                // error
            },
        )

    }
}