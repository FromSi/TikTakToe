package kz.sgq.kotlintictactoe.ui.activity

import android.os.Bundle
import android.view.View
import com.arellomobile.mvp.MvpActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import kotlinx.android.synthetic.main.activity_field.*
import kz.sgq.kotlintictactoe.R
import kz.sgq.kotlintictactoe.mvp.presenter.FieldPresenter
import kz.sgq.kotlintictactoe.mvp.view.FieldView

class FieldActivity : MvpActivity(), FieldView {

    @InjectPresenter
    lateinit var presenter: FieldPresenter

    @ProvidePresenter
    fun provideFieldPresenter(): FieldPresenter {
        return if (intent.getBooleanExtra("mode", true))
            FieldPresenter(true, getString(R.string.step),
                    intent.getStringExtra("playerOne"),
                    intent.getStringExtra("playerTwo"))
        else
            FieldPresenter(false, getString(R.string.step),
                    intent.getStringExtra("playerOne"),
                    intent.getStringExtra("playerTwo"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_field)
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
