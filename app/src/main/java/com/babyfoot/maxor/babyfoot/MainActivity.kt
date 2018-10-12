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


class MainActivity : AppCompatActivity(), DBConnectionListener{
    private val LOG_TAG = MainActivity::class.java!!.getName()
    private var dao: Dao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dao = Dao.instance(this)

        btnStart.setOnClickListener {
            dao!!.connect("127.0.0.1:3306", "MaxorAdmin!", "Maxor", "babyfoot");
            startActivity(intentFor<MatchMakingActivity>())
        }
    }

    override fun onConnectionSuccessful() {
        toast("Succes")
    }

    override fun onConnectionFailed() {
        toast("failed")
    }

    override fun onConnectionStatusInfo(status: String) {
        this.runOnUiThread {
            toast(status)
        }
    }


}
