package com.example.kotlin.activity.invite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.form.User
import com.example.kotlin.activity.data.repo.RepoUser

class InviteRecyclerViewModel : ViewModel() {
    private val repo = RepoUser()
    // firebase 데이터 가져와서 설정해주기
    fun fetchData(): LiveData<MutableList<User>> {
        val mutableData = MutableLiveData<MutableList<User>>()
        repo.getInviteData().observeForever{
            mutableData.value = it
        }
        return mutableData
    }
}