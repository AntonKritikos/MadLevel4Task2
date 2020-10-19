package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var gameRepository: GameRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameRepository = GameRepository(requireContext())
        setScore()

        ivPaperClick.setOnClickListener{
            addGame(1)
        }

        ivRockClick.setOnClickListener {
            addGame(2)
        }

        ivScissorClick.setOnClickListener {
            addGame(3)
        }
    }

    private fun addGame(playerMove: Int) {
        val cMove = (1..3).random()
        val result = checkGameResults(playerMove, cMove)

        mainScope.launch {
            val game = Game(
                result = result,
                cMove = cMove.toShort(),
                pMove = playerMove.toShort(),
                date = Date()
            )
            withContext(Dispatchers.IO) {
                gameRepository.insertGame(game)
            }
        }
        updateUI(playerMove, cMove, result)
        setScore()
    }

    private fun updateUI(pMove: Int, cMove: Int, result: String) {
        tvWinner.text = result
        when (pMove) {
            1 -> ivCResult.setImageResource(R.drawable.paper)
            2 -> ivCResult.setImageResource(R.drawable.rock)
            3 -> ivCResult.setImageResource(R.drawable.scissors)
        }
        when (cMove) {
            1 -> ivPResult.setImageResource(R.drawable.paper)
            2 -> ivPResult.setImageResource(R.drawable.rock)
            3 -> ivPResult.setImageResource(R.drawable.scissors)
        }
    }

    private fun setScore() {
        mainScope.launch {
            val gameDraws = withContext(Dispatchers.IO) {
                gameRepository.countDraws()
            }
            val gameWin = withContext(Dispatchers.IO) {
                gameRepository.countWins()
            }
            val gameLose = withContext(Dispatchers.IO) {
                gameRepository.countLose()
            }
            tvResult.text = getString(R.string.statistic_score, gameWin, gameDraws, gameLose)
        }
    }

    private fun checkGameResults(playerMove: Int, computerMove: Int) : String {
        return if (playerMove == computerMove) {
            getString(R.string.draw)
        } else if ((playerMove == 2 && computerMove == 3) ||
            (playerMove == 1 && computerMove == 2) ||
            (playerMove == 3 && computerMove == 1)) {
            getString(R.string.win)
        } else {
            getString(R.string.lose)
        }
    }

}