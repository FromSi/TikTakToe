package kz.sgq.kotlintictactoe.mvp.view

interface FieldView{
    fun getPlayerOne(): String
    fun getPlayerTwo(): String
    fun setStep(text: String)
    fun setVictoryOne(victory: String)
    fun setVictoryTwo(victory: String)
    fun clearField()
    fun setListBox(i: Int, text: String)
    fun finishActivity()
    fun textColor()
    fun textColorEnd(list: MutableList<Int>)
}