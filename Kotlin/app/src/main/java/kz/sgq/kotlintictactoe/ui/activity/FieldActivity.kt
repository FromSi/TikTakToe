package kz.sgq.kotlintictactoe.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_field.*
import kz.sgq.kotlintictactoe.R
import kz.sgq.kotlintictactoe.mvp.presenter.FieldBotPresenterImpl
import kz.sgq.kotlintictactoe.mvp.presenter.FieldPresenterImpl
import kz.sgq.kotlintictactoe.mvp.presenter.interfaces.FieldPresenter
import kz.sgq.kotlintictactoe.mvp.view.FieldView

class FieldActivity : AppCompatActivity(), FieldView {

    private lateinit var presenter: FieldPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)
        presenter = if (intent.getBooleanExtra("mode", true))
            FieldBotPresenterImpl(this, getString(R.string.step))
        else
            FieldPresenterImpl(this, getString(R.string.step))
    }

    fun onClickBox(view: View) {
        when (view.id) {
            R.id.box11 -> presenter.onClickBox(0)
            R.id.box12 -> presenter.onClickBox(1)
            R.id.box13 -> presenter.onClickBox(2)
            R.id.box21 -> presenter.onClickBox(3)
            R.id.box22 -> presenter.onClickBox(4)
            R.id.box23 -> presenter.onClickBox(5)
            R.id.box31 -> presenter.onClickBox(6)
            R.id.box32 -> presenter.onClickBox(7)
            R.id.box33 -> presenter.onClickBox(8)
            R.id.back -> presenter.onClickBack()
            R.id.clear -> presenter.onClickClear()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun getPlayerOne(): String = intent.getStringExtra("playerOne")

    override fun getPlayerTwo(): String = intent.getStringExtra("playerTwo")

    override fun setStep(text: String) {
        step.text = text
    }

    override fun setVictoryOne(victory: String) {
        playerOne.text = victory
    }

    override fun setVictoryTwo(victory: String) {
        playerTwo.text = victory
    }

    override fun clearField() {
        box11.text = null
        box12.text = null
        box13.text = null
        box21.text = null
        box22.text = null
        box23.text = null
        box31.text = null
        box32.text = null
        box33.text = null
    }

    override fun setListBox(i: Int, text: String) {
        when (i) {
            0 -> box11.text = text
            1 -> box12.text = text
            2 -> box13.text = text
            3 -> box21.text = text
            4 -> box22.text = text
            5 -> box23.text = text
            6 -> box31.text = text
            7 -> box32.text = text
            8 -> box33.text = text
        }
    }

    override fun finishActivity() {
        finish()
    }

    override fun textColor() {
        box11.setTextColor(resources.getColor(R.color.colorText))
        box12.setTextColor(resources.getColor(R.color.colorText))
        box13.setTextColor(resources.getColor(R.color.colorText))
        box21.setTextColor(resources.getColor(R.color.colorText))
        box22.setTextColor(resources.getColor(R.color.colorText))
        box23.setTextColor(resources.getColor(R.color.colorText))
        box31.setTextColor(resources.getColor(R.color.colorText))
        box32.setTextColor(resources.getColor(R.color.colorText))
        box33.setTextColor(resources.getColor(R.color.colorText))
    }

    override fun textColorEnd(list: MutableList<Int>) {
        for (i in 0..8)
            if (list[0] != i && list[1] != i &&
                    list[2] != i)
                boxColor(i)
    }

    private fun boxColor(i: Int) {
        when (i) {
            0 -> box11.setTextColor(resources
                    .getColor(R.color.colorAccent))
            1 -> box12.setTextColor(resources
                    .getColor(R.color.colorAccent))
            2 -> box13.setTextColor(resources
                    .getColor(R.color.colorAccent))
            3 -> box21.setTextColor(resources
                    .getColor(R.color.colorAccent))
            4 -> box22.setTextColor(resources
                    .getColor(R.color.colorAccent))
            5 -> box23.setTextColor(resources
                    .getColor(R.color.colorAccent))
            6 -> box31.setTextColor(resources
                    .getColor(R.color.colorAccent))
            7 -> box32.setTextColor(resources
                    .getColor(R.color.colorAccent))
            8 -> box33.setTextColor(resources
                    .getColor(R.color.colorAccent))
        }
    }

}
