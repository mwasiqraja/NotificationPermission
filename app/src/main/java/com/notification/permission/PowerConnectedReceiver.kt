package com.notification.permission

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PowerConnectedReceiver : BroadcastReceiver(){

    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent!!.action
        Toast.makeText(context!!, "power connected", Toast.LENGTH_SHORT).show()
    }
}