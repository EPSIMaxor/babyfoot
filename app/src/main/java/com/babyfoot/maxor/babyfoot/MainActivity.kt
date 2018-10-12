package com.babyfoot.maxor.babyfoot

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor
import android.os.AsyncTask
import java.sql.Connection
import java.sql.DriverManager
import android.widget.TextView
import org.jetbrains.anko.toast
import org.json.JSONArray
import java.net.URL


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnStart.setOnClickListener {
            var apiResponse = URL("http://localhost:3000/ticket").content
            toast(apiResponse.toString())
//            startActivity(intentFor<MatchMakingActivity>())
        }
    }

}
