package com.example.ipmusic

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.data.UsersDBHendler
import com.data.UsersModel

class LoginActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
    fun goMainMenu(view: View){
        finish()
    }
    fun login(view: View){
        val databaseHandler: UsersDBHendler = UsersDBHendler(this)
        val users: List<UsersModel> = databaseHandler.viewUsers()
        val email=findViewById(R.id.emailFild2) as EditText
        val user=users.find { it.email == email.text.toString()  }
        if(user!=null){
            var password=findViewById(R.id.passwordFild2) as EditText
            if(user.password==password.text.toString()){
                val myDialogFragment = ShowRegistrationInfoFragment()
                val manager = supportFragmentManager
                myDialogFragment.show(manager, "myDialog")
            }
        }
        else{
            Toast.makeText(this, "Invalid email", Toast.LENGTH_SHORT).show()
        }
    }
    fun getInfo(): String {
        val databaseHandler: UsersDBHendler = UsersDBHendler(this)
        val users: List<UsersModel> = databaseHandler.viewUsers()
        val email=findViewById(R.id.emailFild2) as EditText
        val user=users.find { it.email == email.text.toString()  }
        if(user!=null){
            var password=findViewById(R.id.passwordFild2) as EditText
            if(user.password==password.text.toString()){
                return "name: "+user.name+"\nemail: "+user.email;
            }
        }
        return ""
    }
}