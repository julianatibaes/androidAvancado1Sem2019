package com.up.movimento

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.MotionEventCompat
import android.util.Log
import android.view.MotionEvent
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TAG = "TAG: "

        imageView.setOnTouchListener{v, event ->
            val acao = MotionEventCompat.getActionMasked(event)

            when(acao){
                MotionEvent.ACTION_DOWN->{
                    Log.d(TAG, "Action DOWN")
                    imageView.setImageResource(R.color.colorAccent)
                    true
                }
                MotionEvent.ACTION_UP->{
                    Log.d(TAG, "Action UP")
                    imageView.setImageResource(R.color.background_material_dark)
                    true
                }
                MotionEvent.ACTION_MOVE->{
                    Log.d(TAG, "Action MOVE")
                    imageView.setImageResource(android.R.color.holo_blue_light)
                    true
                }
                else -> super.onTouchEvent(event)
            }

        }

        imageView2.setOnLongClickListener {
            Log.d(TAG, "Long click")
            imageView2.setImageResource(android.R.color.holo_blue_light)
            true
        }

        imageView2.setOnClickListener {
            Log.d(TAG, "Click")
            imageView2.setImageResource(
                android.R.color.holo_red_light)
            true
        }
    }
}
