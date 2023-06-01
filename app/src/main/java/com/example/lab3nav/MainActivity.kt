package com.example.lab3nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lab3nav.ui.theme.Lab3NavTheme
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab3NavTheme {
                InventoryApp(scanner = setupScanner())
            }
        }
    }

    private fun setupScanner() : GmsBarcodeScanner {
        

        return GmsBarcodeScanning.getClient(this)
    }
}