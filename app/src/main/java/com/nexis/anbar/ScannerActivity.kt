package com.nexis.anbar

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.ar.core.Point
import com.journeyapps.barcodescanner.BarcodeCallback
import com.journeyapps.barcodescanner.BarcodeResult
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.nexis.anbar.databinding.ActivityScannerBinding

class ScannerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScannerBinding

    private val permissions = arrayOf(
        Manifest.permission.CAMERA
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)


        permissonsResult.launch(permissions)

    }

    private var permissonsResult =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            if (result?.all { it.value } == true) {
                startScanning()
            }
        }

    private fun startScanning() {
        binding.scannerView.decodeSingle(object : BarcodeCallback {
            override fun barcodeResult(result: BarcodeResult?) {
                val qrCodeResult = result?.text
                val returnIntent = Intent()
                returnIntent.putExtra("qrCodeResult", qrCodeResult)
                setResult(Activity.RESULT_OK, returnIntent)
                finish()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        binding.scannerView.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.scannerView.pause()
    }
}