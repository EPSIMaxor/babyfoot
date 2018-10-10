package com.babyfoot.maxor.babyfoot

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.layout_team.view.*

class ImageAdapter(private val mContext: Context, val listTeams: ArrayList<Team>) : BaseAdapter() {
    override fun getItem(p0: Int): Any {
        return listTeams[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return listTeams.size
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val team = this.listTeams[p0]

        val inflator = mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val teamView = inflator.inflate(R.layout.layout_team, null)
        teamView.tvName.text = team.name
        teamView.tvP1.text = team.playerOne.toString()
        teamView.tvP2.text = team.playerTwo.toString()
        if(team.isChecked) {
            teamView.setBackgroundColor(Color.BLUE)
        }else {
            teamView.setBackgroundColor(Color.TRANSPARENT)
        }
        return teamView
    }

}