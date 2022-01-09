package com.example.kotlin.activity.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlin.activity.data.dataclass.Category
import com.example.kotlin.activity.data.repository.CategoryRepo

class CategoryViewModel() : ViewModel() {
    // init
    var Category = MutableLiveData<Category>()

    private val CategoryRepo: CategoryRepo by lazy { CategoryRepo() }

    fun getCategory() {
        // subscribe
        CategoryRepo.getdata().subscribe(
            {
                Category.value = it
            },
            {
                // error
            },
        )

    }
}