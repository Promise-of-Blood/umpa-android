package com.pob.umpa.ui.view.main.sign

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignViewModel : ViewModel() {
    private val _userType = mutableStateOf(UserType.STUDENT)
    val userType: State<UserType> get() = _userType

    fun setUserType(type: UserType) {
        _userType.value = type
    }
}
