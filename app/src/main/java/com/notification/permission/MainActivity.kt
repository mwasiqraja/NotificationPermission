package com.notification.permission

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        askNotificationPermission()
//        getFirebaseregistrationToken()

    }
    fun onTestBranch(){
        print("dfdfq")
    }

    override fun onResume() {
        super.onResume()
        checkNotificationPPermission()
    }

    private fun checkNotificationPPermission() {
        if (Settings.Secure.getString(this.contentResolver, "enabled_notification_listeners")
                .contains(
                    applicationContext.packageName
                )
        ) {
            Toast.makeText(this, "permission already granted", Toast.LENGTH_SHORT).show()
            //service is enabled do something
        } else {
            //service is not enabled try to enabled by calling...
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            applicationContext.startActivity(intent)
        }
    }

/*
    private fun getFirebaseregistrationToken(){
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("TAG", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }

                Log.d("TAG", "getFirebaseregistrationToken: "+task.result)

                // Get new FCM registration token
                *//* val token = task.result

                 // Log and toast
                 val msg = getString(R.string.msg_token_fmt, token)
                 Log.d(TAG, msg)
                 Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()*//*
            })
    }*/

/*    private val requestPermissionLauncher = registerForActivityResult<String, Boolean>(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // FCM SDK (and your app) can post notifications.
        } else {
            // TODO: Inform user that that your app will not show notifications.
        }
    }*/

/*    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("TAG", "askNotificationPermission: ==> granted")
                // FCM SDK (and your app) can post notifications.
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                // TODO: display an educational UI explaining to the user the features that will be enabled
                //       by them granting the POST_NOTIFICATION permission. This UI should provide the user
                //       "OK" and "No thanks" buttons. If the user selects "OK," directly request the permission.
                //       If the user selects "No thanks," allow the user to continue without notifications.
                Log.d("TAG", "askNotificationPermission: ==> should ")
                val uri = Uri.fromParts(
                    "package",
                    this.packageName,
                    null
                )
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
                intent.data = uri
                startActivityForResult(intent, 1001)
            } else {
                // Directly ask for the permission
                requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                Log.d("TAG", "askNotificationPermission: ==> request")
            }
        }
    }*/

}