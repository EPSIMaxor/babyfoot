package com.babyfoot.maxor.babyfoot

import android.content.Intent
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_match.*
import kotlinx.android.synthetic.main.activity_match_making.*
import org.jetbrains.anko.intentFor
import java.io.Serializable

class MatchMakingActivity : AppCompatActivity() {
    var listTeam = ArrayList<Team>()
    var teams = ArrayList<Team>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_making)
//        val btnValidez: Button = findViewById(R.id.btnValidez)
        btnValidez.setOnClickListener {
            teams = match(listTeam)
            startActivity(intentFor<MatchActivity>("Teams" to teams))
        }

        createTeams()

        val gridview: GridView = gvTeam
        gridview.adapter = ImageAdapter(this,listTeam)

        gridview.onItemClickListener = AdapterView.OnItemClickListener { parent, v, position, id ->
            if (matchMaking(listTeam) && !listTeam.get(position).isChecked) {
                Toast.makeText(this,"Impossible de saisir plus de deux équipe !", Toast.LENGTH_SHORT).show()
            }else if (!matchMaking(listTeam)){
                listTeam.get(position).isChecked = !listTeam.get(position).isChecked
                if(matchMaking(listTeam)) {
                    btnValidez.visibility = View.VISIBLE
                }
                gridview.adapter = ImageAdapter(this,listTeam)
            }else if (matchMaking(listTeam) && listTeam.get(position).isChecked) {
                listTeam.get(position).isChecked = !listTeam.get(position).isChecked
                btnValidez.visibility = View.INVISIBLE
                gridview.adapter = ImageAdapter(this,listTeam)
            }
        }
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

        listTeam.add(t1)
        listTeam.add(t2)
        listTeam.add(t3)
        listTeam.add(t4)
        listTeam.add(t5)

    }

    open fun matchMaking(list: ArrayList<Team>): Boolean {
        var check: Boolean = false
        var cpt: Int = 0
        for (t: Team in list) {
            if(t.isChecked) {
                cpt++
            }
            if(cpt == 2) {
                check = true
                break
            }
        }
        return check
    }

    open fun match(list: ArrayList<Team>): ArrayList<Team> {
        val matchTeam = ArrayList<Team>()
        for (t: Team in list) {
            if(t.isChecked) {
                matchTeam.add(t)
            }
        }
        return matchTeam
    }
}
