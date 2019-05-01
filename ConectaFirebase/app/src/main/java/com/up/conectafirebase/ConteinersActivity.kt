package com.up.conectafirebase

import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_conteiners.*

class ConteinersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conteiners)

        carta.visibility = View.GONE
        carta.setOnClickListener {
            // código
        }
    }
}
