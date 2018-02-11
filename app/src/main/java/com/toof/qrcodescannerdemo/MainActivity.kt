package com.toof.qrcodescannerdemo

/**
 * Created by Toof on 2/10/2018.
 */

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

import com.google.android.gms.vision.barcode.Barcode

class MainActivity : AppCompatActivity() {
    private lateinit var btnScan: Button
    private lateinit var tvResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnScan = findViewById(R.id.scanbtn)
        tvResult = findViewById(R.id.result)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), PERMISSION_REQUEST)
        }
        btnScan.setOnClickListener {
            val intent = Intent(this@MainActivity, ScanActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val barcode = data.getParcelableExtra<Barcode>("barcode")
                tvResult.post { tvResult.text = barcode.displayValue }
            }
        }
    }

    companion object {
        val REQUEST_CODE = 100
        val PERMISSION_REQUEST = 200
    }
}