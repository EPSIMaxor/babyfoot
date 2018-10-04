package com.babyfoot.maxor.babyfoot

open class Person (var name: String, var lastName: String){
    val team: Team? = null

    override fun toString(): String = "$name.$lastName"

}