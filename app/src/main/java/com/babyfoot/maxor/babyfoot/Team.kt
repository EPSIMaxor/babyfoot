package com.babyfoot.maxor.babyfoot

import java.io.Serializable

open class Team(var name: String, var playerOne: Person, var playerTwo: Person): Serializable {
    var isChecked: Boolean = false

    override fun toString(): String = """
                                    | Equipe : $name
                                    | Player 1 : ${playerOne.toString()}
                                    | Player 2 : ${playerTwo.toString()}""".trimMargin()

}