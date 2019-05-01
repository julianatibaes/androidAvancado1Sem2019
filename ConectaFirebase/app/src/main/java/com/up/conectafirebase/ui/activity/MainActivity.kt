package com.up.conectafirebase.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.up.conectafirebase.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView.setText(R.string.descricao.toString())

        imageView.setImageResource(R.drawable.abc_ab_share_pack_mtrl_alpha)
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE


        if (editText.text.toString().isNotBlank()){
            var valorTexto = editText.text.toString()
            valorTexto = valorTexto +
                    " " +
                    R.string.descricao
            editText.setText(valorTexto)
        }



    }
}
