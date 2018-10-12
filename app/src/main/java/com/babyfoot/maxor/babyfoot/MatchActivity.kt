package com.babyfoot.maxor.babyfoot

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_match.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton


class MatchActivity : AppCompatActivity(), View.OnClickListener {
    var scoreT1: Int = 0
    var scoreT2: Int = 0
    var list = ArrayList<Team>()
    lateinit var dialog: AlertDialog
    lateinit var builder: AlertDialog.Builder


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
        btnP1.setOnClickListener(this)
        btnP2.setOnClickListener(this)
        btnEnd.setOnClickListener(this)
        list = intent.extras.getSerializable("Teams") as ArrayList<Team>

        builder =  AlertDialog.Builder(this)
//        val alertDialog = AlertDialog.Builder(this).create()

        // Setting Dialog Title
        builder.setTitle("Alert Dialog")

        // Setting Dialog Message
        builder.setMessage("Etes vous sur de vouloir quitter le match ?")
                .setCancelable(false)
                .setPositiveButton("OK") { dialog, id -> finish() }
                .setNegativeButton("Annuler") {dialog, id -> dialog.dismiss()}

        // Setting Icon to Dialog
        builder.setIcon(R.drawable.ic_home_black_24dp)


        // Showing Alert Message
//        alertDialog.show()
        dialog = builder.create()
        dialog.setTitle("Attention !")
        dialog.setOnKeyListener(object : DialogInterface.OnKeyListener {
            override fun onKey(p0: DialogInterface?, p1: Int, p2: KeyEvent?): Boolean {
                if (p1 == KeyEvent.KEYCODE_BACK) {
                    dialog.dismiss()
                }
                return true
            }

        })

    }

    override fun onBackPressed() {
        alert("Etes vous sur de vouloir quitter le match ?","Attention !") {
            yesButton {
                ///PERFORM ANY TASK HERE
                finish()
            }
            noButton {

            }
        }.show()
//        dialog.show()
    }

    override fun onStart() {
        super.onStart()
        tvT1.text = list.get(0).name
        tvT2.text = list.get(1).name
        tvS1.text = scoreT1.toString()
        tvS2.text = scoreT2.toString()
    }

    override fun onStop() {
        super.onStop()

    }

    override fun onRestart() {
        super.onRestart()

    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btnP1 -> {
                scoreT1++
                tvS1.text = scoreT1.toString()
            }
            R.id.btnP2 -> {
                scoreT2++
                tvS2.text = scoreT2.toString()
            }
            R.id.btnEnd -> {
//              start new activity
                scoreT1 = 0
                scoreT2 = 0
                tvS1.text = scoreT1.toString()
                tvS2.text = scoreT2.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt("score1", scoreT1)
        outState?.putInt("score2", scoreT2)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        scoreT1 = savedInstanceState?.getInt("score1")!!
        scoreT2 = savedInstanceState?.getInt("score2")!!
        tvS1.text = scoreT1.toString()
        tvS2.text = scoreT2.toString()
    }
}
