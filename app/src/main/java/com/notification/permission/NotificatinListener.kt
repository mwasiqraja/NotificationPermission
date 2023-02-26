package com.notification.permission

import android.app.Notification
import android.app.Person
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

class NotificatinListener : NotificationListenerService() {

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        Log.d("TAG", "onNotificationPosted: "+sbn?.packageName)
        val from: String? = sbn?.notification?.extras?.getString(NotificationCompat.EXTRA_TITLE)
        val message: String? = sbn?.notification?.extras?.getString(NotificationCompat.EXTRA_TEXT, "")
//        maybePopulatePeople(sbn?.notification!!)
      /*  if (sbn?.notification?.extras?.get("android.people.list")!=null){
            val uri = ((sbn.notification?.extras?.get("android.people.list") as ArrayList<Person>)[0]).uri
            val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, Uri.parse(uri))
            Log.i("TAG", "From: $from")
            Log.i("TAG", "Message: $message")
        }*/
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification?) {
        super.onNotificationRemoved(sbn)
        Log.d("TAG", "onNotificationRemoved: "+sbn?.packageName)
    }


    @RequiresApi(Build.VERSION_CODES.P)
    private fun maybePopulatePeople(notification: Notification) {
        if (getApplicationInfo().targetSdkVersion < Build.VERSION_CODES.P) {
            val people: ArrayList<Person> = notification.extras.getParcelableArrayList(
                Notification.EXTRA_PEOPLE_LIST
            )!!
            if (people.isEmpty()) {
                val size = people.size
                val peopleArray = arrayOfNulls<String>(size)
                for (i in 0 until size) {
                    val person = people[i]
                    peopleArray[i] = person.uri
                }
                notification.extras.putStringArray(Notification.EXTRA_PEOPLE, peopleArray)
            }
        }
    }

}