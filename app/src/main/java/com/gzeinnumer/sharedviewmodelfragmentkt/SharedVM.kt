package com.gzeinnumer.sharedviewmodelfragmentkt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedVM : ViewModel(){

    val selected = MutableLiveData<String>()

    fun select(s: String) {
        selected.value = s
    }

}