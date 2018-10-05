package com.babyfoot.maxor.babyfoot

open class Team(var name: String, var playerOne: Person, var playerTwo: Person) {

    override fun toString(): String = """
                                    | Equipe : $name
                                    | Player 1 : ${playerOne.toString()}
                                    | Player 2 : ${playerTwo.toString()}""".trimMargin()

}