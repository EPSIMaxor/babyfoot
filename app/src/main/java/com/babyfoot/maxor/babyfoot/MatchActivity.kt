package com.babyfoot.maxor.babyfoot

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity(), View.OnClickListener {
    var scoreT1: Int = 0
    var scoreT2: Int = 0
    var list = ArrayList<Team>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)
//        val tvT1: TextView = findViewById(R.id.tvT1)
//        val tvT2: TextView = findViewById(R.id.tvT2)
//        val tvS1: TextView = findViewById(R.id.tvS1)
//        val tvS2: TextView = findViewById(R.id.tvS2)
//        val btnP1: Button = findViewById(R.id.btnP1)
//        val btnP2: Button = findViewById(R.id.btnP2)
//        val btnEnd: Button = findViewById(R.id.btnEnd)
        btnP1.setOnClickListener(this)
        btnP2.setOnClickListener(this)
        btnEnd.setOnClickListener(this)

            list = intent.extras.getSerializable("Teams") as ArrayList<Team>
//            tvT1.text = list.get(0).name
//            tvT2.text = list.get(1).name
//            tvS1.text = scoreT1.toString()
//            tvS2.text = scoreT2.toString()
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
        saveScoreToPrefs()
    }

    override fun onRestart() {
        super.onRestart()
        scoreT1 = restoreScoreFromPrefs()
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

    /** Attempts to read the externally saved counter value and update the model.   */
    protected fun restoreScoreFromPrefs(): Int {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val value = sharedPref.getInt("Score1", 0)
        return value
    }

    /** Saves the counter value externally.  */
    protected fun saveScoreToPrefs() {
        val sharedPref = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putInt("Score1", scoreT1)
        editor.commit()
    }
}
