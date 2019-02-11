package com.mooo.ewolvy.filedialogtests

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.mooo.ewolvy.filechooser.FileChooserActivity
import android.support.design.widget.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

const val REQUEST_CODE_FC = 0

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startFileChooser(v: View) {
        /*intent = Intent(this@MainActivity, FileChooserActivity::class.java)
        startActivityForResult(intent, REQUEST_CODE_FC)*/
        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)

        startActivityForResult(Intent.createChooser(intent, "Select a file"), REQUEST_CODE_FC)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode){
            REQUEST_CODE_FC ->
                if (resultCode == Activity.RESULT_OK) {
                    Snackbar.make(
                        root_layout, // Parent view
                        data?.data.toString() ?: "ok", // Message to show
                        Snackbar.LENGTH_LONG // How long to display the message.
                    ).show()
                } else if (resultCode == Activity.RESULT_CANCELED) {
                    Snackbar.make(
                        root_layout,
                        "error",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

            else -> Log.d(localClassName, "Unexpected activity return")
        }
    }
}
