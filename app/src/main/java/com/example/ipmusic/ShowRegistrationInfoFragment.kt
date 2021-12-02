package com.example.ipmusic

import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

public class ShowRegistrationInfoFragment: DialogFragment() {
    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val data=(activity as RegistrationActivity).getInfo()
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Your info")
                .setMessage(data)
                .setPositiveButton("ОК") {
                        dialog, id ->  dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }*/
}