package kz.sgq.kotlintictactoe.mvp.model.interfaces

interface FieldModel {
    fun getScorePlayerOne(): String
    fun getScorePlayerTwo(): String
    fun getStep(): String
    fun onClickBox(i: Int): Boolean
    fun isStep(): Boolean
    fun isMode(): Boolean
    fun checkVictory(): Boolean
    fun checkDraw(): Boolean
    fun getEnd(): Boolean
    fun stepBot(): Int
    fun getBoxWinList(): MutableList<Int>
    fun setStep(boolean: Boolean)
    fun setMode(mode: Boolean)
    fun createLists()
    fun clearScore()
    fun setStepText(string: String)
}