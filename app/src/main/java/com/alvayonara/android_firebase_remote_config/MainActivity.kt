package com.alvayonara.android_firebase_remote_config

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), ForceUpdateChecker.OnUpdateNeededListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ForceUpdateChecker.with(this).onUpdateNeeded(this).check()
    }

    override fun onUpdateNeeded(updateUrl: String?) {
        val dialog = AlertDialog.Builder(this)
            .setTitle("New version available")
            .setMessage("Please, update app to new version to continue reposting.")
            .setPositiveButton(
                "Update"
            ) { _, _ -> updateUrl?.let { redirectStore(it) } }
            .setNegativeButton(
                "No, thanks"
            ) { _, _ -> finish() }.create()
        dialog.show()
    }

    private fun redirectStore(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }
}