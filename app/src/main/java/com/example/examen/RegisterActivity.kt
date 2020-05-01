package com.example.examen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*

class RegisterActivity : AppCompatActivity() {

    lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerViewModel = RegisterViewModel(RegisterRepository())
        registerViewModel.model.observe(this, Observer(::updateUi))
        button.setOnClickListener {
            registerViewModel.doRegister(name.text.toString())
        }
    }

    private fun updateUi(model: RegisterViewModel.UiModel?) {
        loading_progress_bar.visibility = if ( model is RegisterViewModel.UiModel.Loading) View.VISIBLE else View.GONE
        when ( model ) {
            is RegisterViewModel.UiModel.Register -> validateRegister(model.success)        }    }
    private fun validateRegister( resp: Boolean) {
        var message="Registro fallido"

        if( resp) {
           message="Registro exitoso"
        }

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Alert")
        builder.setMessage(message)
        builder.show()

    }
}
