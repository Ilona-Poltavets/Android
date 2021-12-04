package com.example.ipmusic

import android.os.Build
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.net.Uri


class PlayerActivity : AppCompatActivity() {
    companion object {
        const val IDM_BACK = 101
        const val IDM_NEXT = 102
    }

    private lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)
        container = findViewById(R.id.window)
        registerForContextMenu(container)

        val button: Button = findViewById(R.id.button)

        val popupMenu2 = PopupMenu(this, button)
        popupMenu2.inflate(R.menu.popup_menu)
        popupMenu2.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.fb_share_ico -> {
                    Toast.makeText(this, "Facebook", Toast.LENGTH_SHORT).show()
                }
                R.id.tw_share_ico -> {
                    Toast.makeText(this, "Twitter", Toast.LENGTH_SHORT).show()
                }
                R.id.tg_share_ico -> {
                    Toast.makeText(this, "Telegram", Toast.LENGTH_SHORT).show()
                }
            }
            false
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            popupMenu2.setForceShowIcon(true)
        }

        button.setOnClickListener {
            popupMenu2.show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.sub_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    fun goMainMenu(view: View) {
        finish()
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(Menu.NONE, IDM_BACK, Menu.NONE, "Back")
        menu?.add(Menu.NONE, IDM_NEXT, Menu.NONE, "Next")
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {

        val message: CharSequence = when (item.itemId) {
            IDM_BACK -> "You select Back"
            IDM_NEXT -> "You select Next"
            else -> return super.onContextItemSelected(item)
        }

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

        return true
    }
    fun openFacebook(item: MenuItem) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.facebook.com/mistikcsgo"))
        startActivity(intent)
    }
    fun openTelegram(item: MenuItem) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/Ilona_Poltavets"))
        startActivity(intent)
    }
    fun openTwitter(item: MenuItem) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/IPoltawets"))
        startActivity(intent)
    }
}