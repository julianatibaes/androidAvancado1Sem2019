package com.up.conectafirebase

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Switch
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_button.*

class ButtonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button)

        button2.setOnClickListener {
            // código
        }

    }


    fun switchClicado(view: View){
        if(view is Switch){
            val checked: Boolean = view.isChecked

            when(view.id){
                R.id.switch1 -> {
                    if(checked){
                        // código
                    }
                    else {
                        // código
                    }
                }
            }
        }
    }

    fun checkBoxClicado(view: View){
        if(view is CheckBox){
            val checked: Boolean = view.isChecked

            when(view.id){
                R.id.checkBox -> {
                    if(checked){
                        // código
                    }
                    else {
                        // código
                    }
                }
            }
        }
    }

}
