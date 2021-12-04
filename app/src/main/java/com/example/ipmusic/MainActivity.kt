package com.example.ipmusic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //start animation text
        val text=findViewById(R.id.title) as TextView
        val animation = AnimationUtils.loadAnimation(
            applicationContext,
            R.anim.start_text
        )
        text.startAnimation(animation)
    }

    //create option menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    //transition to other activities
    fun showRegistration(view: View) {
        val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
        startActivity(intent)
    }
    fun showLogin(view: View) {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }
    fun showPlayer(view: View) {
        val intent = Intent(this@MainActivity, PlayerActivity::class.java)
        startActivity(intent)
    }
    fun showListUsers(view: View){
        val intent=Intent(this@MainActivity, UsersActivity::class.java)
        startActivity(intent)
    }
    fun showPlayer(item: MenuItem) {
        val intent = Intent(this@MainActivity, PlayerActivity::class.java)
        startActivity(intent)
    }
    fun showRegistration(item: MenuItem) {
        val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
        startActivity(intent)
    }
    fun showLogin(item: MenuItem) {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
    }
}