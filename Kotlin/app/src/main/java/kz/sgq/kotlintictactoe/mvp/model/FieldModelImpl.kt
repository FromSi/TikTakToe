package kz.sgq.kotlintictactoe.mvp.model

import kz.sgq.kotlintictactoe.mvp.model.interfaces.FieldModel
import kz.sgq.kotlintictactoe.utils.AnswerField
import java.util.*

const val ANSWER_NULL: Int = -1

class FieldModelImpl(
        var playerOne: String,
        var playerTwo: String
) : FieldModel {

    private var stepText: String? = null
    private var scorePlayerOne: Int = 0
    private var scorePlayerTwo: Int = 0
    private var step: Boolean = true
    private var mode: Boolean = true
    private var end: Boolean = false
    private var listBoxOne: MutableList<Int> = arrayListOf()
    private var listBoxTwo: MutableList<Int> = arrayListOf()
    private var boxWinList: MutableList<Int> = arrayListOf()

    init {
        createLists()
    }

    override fun getScorePlayerOne(): String = "$playerOne $scorePlayerOne"

    override fun getScorePlayerTwo(): String = "$scorePlayerTwo $playerTwo"

    override fun getStep(): String = if (step) "$stepText $playerOne"
    else "$stepText $playerTwo"

    override fun onClickBox(i: Int): Boolean {
        if (listBoxOne[i] == ANSWER_NULL &&
                listBoxTwo[i] == ANSWER_NULL) {
            if (step)
                listBoxOne[i] = i
            else
                listBoxTwo[i] = i
            return true
        }
        return false
    }

    override fun isStep(): Boolean = step

    override fun isMode(): Boolean {
        return mode
    }

    override fun setMode(mode: Boolean) {
        this.mode = mode
    }
    override fun checkVictory(): Boolean {
        return if (step)
            if (checkBox(listBoxOne)) {
                scorePlayerOne++
                end = true
                true
            } else
                false
        else
            if (checkBox(listBoxTwo)) {
                scorePlayerTwo++
                end = true
                true
            } else
                false
    }

    override fun checkDraw(): Boolean {
        for (i in 0..8)
            if (listBoxOne[i] == ANSWER_NULL &&
                    listBoxTwo[i] == ANSWER_NULL)
                return false
        return true
    }

    override fun getEnd(): Boolean = end

    override fun stepBot(): Int {
        val checkStepWin = checkStepBot(listBoxTwo)
        return if (checkStepWin == ANSWER_NULL) {
            val checkStepEnemy = checkStepBot(listBoxOne)
            if (checkStepEnemy == ANSWER_NULL) {
                val list: MutableList<Int> = stepRandomBot()
                val random = Random(System.currentTimeMillis())
                list[random.nextInt(list.size)]
            } else
                checkStepEnemy
        } else
            checkStepWin
    }

    override fun getBoxWinList(): MutableList<Int> = boxWinList

    override fun setStep(boolean: Boolean) {
        step = boolean
    }

    override fun createLists() {
        boxWinList.clear()
        listBoxOne.clear()
        listBoxTwo.clear()
        for (i in 0..8) {
            listBoxOne.add(-1)
            listBoxTwo.add(-1)
        }
        end = false
    }

    override fun clearScore() {
        scorePlayerOne = 0
        scorePlayerTwo = 0
    }

    override fun setStepText(string: String) {
        stepText = string
    }

    private fun checkBox(list: MutableList<Int>): Boolean {
        for (i in 0..7) {
            var boxOne = false
            var boxTwo = false
            var boxThree = false

            if (AnswerField.getAnswer(i, 0) in list) boxOne = true
            if (AnswerField.getAnswer(i, 1) in list) boxTwo = true
            if (AnswerField.getAnswer(i, 2) in list) boxThree = true

            if (boxOne && boxTwo && boxThree) {
                boxWinList.add(AnswerField.getAnswer(i, 0))
                boxWinList.add(AnswerField.getAnswer(i, 1))
                boxWinList.add(AnswerField.getAnswer(i, 2))
                return true
            }
        }
        return false
    }

    private fun checkStepBot(list: MutableList<Int>): Int {
        for (i in 0..7) {
            var boxOne = false
            var boxTwo = false
            var boxThree = false

            if (AnswerField.getAnswer(i, 0) in list) boxOne = true
            if (AnswerField.getAnswer(i, 1) in list) boxTwo = true
            if (AnswerField.getAnswer(i, 2) in list) boxThree = true

            val answerOne: Int = AnswerField.getAnswer(i, 2)
            val answerTwo: Int = AnswerField.getAnswer(i, 1)
            val answerThree: Int = AnswerField.getAnswer(i, 0)

            if (boxOne && boxTwo &&
                    listBoxOne[answerOne] == ANSWER_NULL &&
                    listBoxTwo[answerOne] == ANSWER_NULL)
                return answerOne
            if (boxOne && boxThree &&
                    listBoxOne[answerTwo] == ANSWER_NULL &&
                    listBoxTwo[answerTwo] == ANSWER_NULL)
                return answerTwo
            if (boxTwo && boxThree &&
                    listBoxOne[answerThree] == ANSWER_NULL &&
                    listBoxTwo[answerThree] == ANSWER_NULL)
                return answerThree
        }
        return -1
    }

    private fun stepRandomBot(): MutableList<Int> {
        val list: MutableList<Int> = arrayListOf()
        for (i in 0..7) {
            val answerOne = AnswerField.getAnswer(i, 0)
            val answerTwo = AnswerField.getAnswer(i, 1)
            val answerThree = AnswerField.getAnswer(i, 2)
            for (item in listBoxOne) {
                if (answerOne != item &&
                        listBoxOne[answerOne] == ANSWER_NULL &&
                        listBoxTwo[answerOne] == ANSWER_NULL)
                    list.add(answerOne)
                if (answerTwo != item &&
                        listBoxOne[answerTwo] == ANSWER_NULL &&
                        listBoxTwo[answerTwo] == ANSWER_NULL)
                    list.add(answerTwo)
                if (answerThree != item &&
                        listBoxOne[answerThree] == ANSWER_NULL &&
                        listBoxTwo[answerThree] == ANSWER_NULL)
                    list.add(answerThree)
            }
        }
        return list
    }
}