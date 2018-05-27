package kz.sgq.kotlintictactoe.mvp.view

import com.arellomobile.mvp.MvpView

interface FieldView : MvpView{
    fun setStep(text: String)
    fun setVictoryOne(victory: String)
    fun setVictoryTwo(victory: String)
    fun setListBox(i: Int, text: String)
    fun textColorEnd(list: MutableList<Int>)
    fun clearField()
    fun finishActivity()
    fun textColor()
}