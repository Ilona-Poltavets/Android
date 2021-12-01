package com.example.ipmusic

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegistrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_layout)
    }

    fun goMainMenu(view: View) {
        finish()
    }

    fun showFragment(view: View) {
        val myDialogFragment = ShowRegistrationInfoFragment()
        val manager = supportFragmentManager
        myDialogFragment.show(manager, "myDialog")
    }

    fun getInfo(): String {
        var data:String
        var email = findViewById(R.id.emailFild) as EditText
        var name = findViewById(R.id.nameFild) as EditText
        var password = findViewById(R.id.passwordFild) as EditText
        var comfirmPassword = findViewById(R.id.confirmPasswordFild) as EditText
        if(email.text.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email.text).matches()){
            if(name.text.isNotEmpty()){
                if(password.text.isNotEmpty() && password.text.toString()==comfirmPassword.text.toString()){
                    data = "email: " + email.text.toString() + "\nname: " + name.text.toString()
                    return data
                }
                else{
                    Toast.makeText(this, "Password mismatch", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Enter your name", Toast.LENGTH_SHORT).show()
            }
        }
        else{
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
        }
        return "Error"
    }
}