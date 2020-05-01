package com.example.examen

import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel(val registerRepository: RegisterRepository) : ViewModel() {
    val model: LiveData<UiModel>
        get() = _model
    private val _model = MutableLiveData<UiModel>()
    sealed class UiModel {
        class Register(val success: Boolean): UiModel()
        object Loading: UiModel()
    }
    fun doRegister(userName: String) {
        _model.value = UiModel.Loading
        val runnable = Runnable {
            _model.value = UiModel.Register( registerRepository.saveUser(userName))
        }
        Handler().postDelayed(runnable, 3000)
    }}