package com.example.kotlin.activity.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.dataclass.PersonalCoin
import com.example.kotlin.activity.data.repository.PersonalCoinRepo

class PersonalCoinViewModel() : ViewModel() {
    // init
    var PersonalCoin = MutableLiveData<PersonalCoin>()

    private val PersonalCoinRepo: PersonalCoinRepo by lazy { PersonalCoinRepo() }

    fun getPersonalCoin() {
        // subscribe
        PersonalCoinRepo.getdata().subscribe(
            {
                PersonalCoin.value = it
            },
            {
                // error
            },
        )

    }
}