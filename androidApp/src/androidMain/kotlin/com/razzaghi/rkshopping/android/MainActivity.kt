package com.razzaghi.rkshopping.android

//import MainView
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.razzaghi.shopingbykmp.android.MainView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // initKoin()

        setContent {
            MainView(application)
        }
    }
}