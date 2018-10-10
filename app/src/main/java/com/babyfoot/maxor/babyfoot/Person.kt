package com.babyfoot.maxor.babyfoot

import java.io.Serializable

open class Person (var name: String, var lastName: String): Serializable {
    val team: Team? = null

    override fun toString(): String = "$name.$lastName"

}