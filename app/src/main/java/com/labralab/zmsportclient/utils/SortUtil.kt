package com.labralab.zmsportclient.utils

import com.labralab.zmsportclient.models.Team
import com.labralab.zmsportclient.models.Tournament
import java.util.*

/**
 * Created by pc on 11.04.2018.
 */
class SortUtil {

    companion object {

        fun sort(list: List<Team>): List<Team>{

            var result = list.sortedWith(compareBy({it.points}, {it.plusMinus}, {it.games}))
            result = result.reversed()

            for(i in 1 until result.size){

                //if equals results
                if(result[i].points == result[i - 1].points){

                    val tournament = Tournament.getInstance()
                    val teamOne = result[i - 1]
                    val teamTwo = result[i]
                    val gamePos = TournamentUtil.getGame(tournament.gameList,
                            teamOne.title,
                            teamTwo.title)

                    //played with each other
                    if(gamePos != -1){

                        val game = tournament.gameList[gamePos]

                        //if teamOne - winner
                        if(game.score_1 > game.score_2){

                            if(teamOne.title == game.team_2.title){

                                Collections.swap(result, i - 1, i)

                            }

                            //if teamTwo - winner
                        } else {

                            if(teamOne.title == game.team_1.title){

                                Collections.swap(result, i - 1, i)

                            }
                        }
                    }
                }
            }

            return result
        }
    }
}