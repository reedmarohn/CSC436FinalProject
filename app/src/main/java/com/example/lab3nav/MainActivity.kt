package com.example.lab3nav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.lab3nav.ui.InventoryViewModel
import com.example.lab3nav.ui.theme.Lab3NavTheme
import com.google.mlkit.vision.codescanner.GmsBarcodeScanner
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab3NavTheme {
                val viewModel : InventoryViewModel = viewModel(factory = InventoryViewModel.Factory)
                InventoryApp(viewModel = viewModel, scanner = setupScanner())
            }
        }
    }

    private fun setupScanner() : GmsBarcodeScanner {
        return GmsBarcodeScanning.getClient(this)
    }
}