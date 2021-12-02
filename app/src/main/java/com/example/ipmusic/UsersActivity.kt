package com.example.ipmusic

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.data.UsersDBHendler
import com.data.UsersModel

class UsersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_users)
    }

    fun goMainMenu(view: View) {
        finish()
    }

    //method for read records from database in ListView
    fun viewRecord(view: View) {
        val databaseHandler: UsersDBHendler = UsersDBHendler(this)
        val users: List<UsersModel> = databaseHandler.viewUsers()
        val userArrayId = Array<String>(users.size) { "0" }
        val userArrayName = Array<String>(users.size) { "null" }
        val userArrayEmail = Array<String>(users.size) { "null" }
        var index = 0
        for (u in users) {
            userArrayId[index] = u.id.toString()
            userArrayName[index] = u.name
            userArrayEmail[index] = u.email
            index++
        }
        val listAdapter = ListAdapter(this, userArrayId, userArrayName, userArrayEmail)
        val listView=findViewById(R.id.listView) as ListView
        listView.adapter = listAdapter
    }
    //method for deleting records based on id
    fun deleteRecord(view: View) {
        val dialogBuilder = AlertDialog.Builder(this)
        val inflater = this.layoutInflater
        val dialogView = inflater.inflate(R.layout.delete_dialog, null)
        dialogBuilder.setView(dialogView)

        val dltId = dialogView.findViewById(R.id.deleteId) as EditText
        dialogBuilder.setTitle("Delete Record")
        dialogBuilder.setMessage("Enter id below")
        dialogBuilder.setPositiveButton("Delete", DialogInterface.OnClickListener { _, _ ->

            val deleteId = dltId.text.toString()
            val databaseHandler: UsersDBHendler = UsersDBHendler(this)
            if (deleteId.trim() != "") {
                val status = databaseHandler.deleteEmployee(
                    UsersModel(
                        Integer.parseInt(deleteId),
                        "",
                        "",
                        ""
                    )
                )
                if (status > -1) {
                    Toast.makeText(applicationContext, "record deleted", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "id or name or email cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }

        })
        dialogBuilder.setNegativeButton("Cancel", DialogInterface.OnClickListener { _, _ -> })
        val b = dialogBuilder.create()
        b.show()
    }
}