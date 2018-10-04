package com.babyfoot.maxor.babyfoot

import android.app.Fragment
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
                val fraghome = HomeFragment.newInstance()
//                pushFragment(fraghome)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun createTeams() {
        val p1 =  Person("Jacque","Perreau")
        val p2 =  Person("Patrick","Orain")
        val p3 =  Person("Samuel","nurib")
        val p4 =  Person("Claire","rego")
        val p5 =  Person("Clement","Benoit")
        val p6 =  Person("Pierre","Faril")
        val p7 =  Person("Damien","Quiro")
        val p8 =  Person("Aurelien","girgon")
        val p9 =  Person("Maxime","Duffeau")
        val p10 =  Person("Enzo","Rotin")

        val t1 =  Team("JP", p1, p2)
        val t2 =  Team("PO",p3, p4)
        val t3 =  Team("SN",p5, p6)
        val t4 =  Team("CR",p7, p8)
        val t5 =  Team("CB",p9, p10)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        createTeams()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)


    }


//    private fun pushFragment(fragment: Fragment?) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.container, fragment)
//        transaction.addToBackStack(null)
//    transaction.commit()
//}
}
