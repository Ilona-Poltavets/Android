package com.example.ipmusic

import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.data.UsersDBHendler
import com.data.UsersModel

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_layout)
    }

    fun goMainMenu(view: View) {
        finish()
    }

    fun saveRecord(view: View) {
        val name = findViewById(R.id.nameFild) as TextView
        val email = findViewById(R.id.emailFild) as TextView
        val password = findViewById(R.id.passwordFild) as EditText
        val comfirmPassword = findViewById(R.id.confirmPasswordFild) as EditText
        val databaseHandler = UsersDBHendler(this)

        if (email.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.text).matches()) {
            if (name.text.isNotEmpty()) {
                if (password.text.isNotEmpty() && password.text.toString() == comfirmPassword.text.toString()) {
                    val status = databaseHandler.addUser(
                        UsersModel(
                            0,
                            email.text.toString(),
                            name.text.toString(),
                            password.text.toString()
                        )
                    )
                    if (status > -1) {
                        Toast.makeText(applicationContext, "record save", Toast.LENGTH_LONG).show()
                        name.setText("")
                        email.setText("")
                        password.setText("")
                        comfirmPassword.setText("")
                    }
                } else {
                    Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
        }
    }
}