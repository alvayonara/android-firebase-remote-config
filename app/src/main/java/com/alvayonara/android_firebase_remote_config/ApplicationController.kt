package com.alvayonara.android_firebase_remote_config

import android.app.Application
import com.google.firebase.remoteconfig.FirebaseRemoteConfig


class ApplicationController : Application() {

    override fun onCreate() {
        super.onCreate()

        val mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()
        mFirebaseRemoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

        mFirebaseRemoteConfig.fetch(60)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    mFirebaseRemoteConfig.activate()
                }
            }
    }
}