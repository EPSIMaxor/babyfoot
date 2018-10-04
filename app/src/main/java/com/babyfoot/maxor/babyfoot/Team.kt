package com.babyfoot.maxor.babyfoot

open class Team(var team: String, var playerOne: Person, var playerTwo: Person) {

    override fun toString(): String = """
                                    | Equipe : $team
                                    | Player 1 : ${playerOne.toString()}
                                    | Player 2 : ${playerTwo.toString()}""".trimMargin()

}