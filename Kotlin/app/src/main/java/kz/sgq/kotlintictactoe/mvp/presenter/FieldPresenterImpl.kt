package kz.sgq.kotlintictactoe.mvp.presenter

import kz.sgq.kotlintictactoe.mvp.model.FieldModelImpl
import kz.sgq.kotlintictactoe.mvp.model.interfaces.FieldModel
import kz.sgq.kotlintictactoe.mvp.presenter.interfaces.FieldPresenter
import kz.sgq.kotlintictactoe.mvp.view.FieldView

class FieldPresenterImpl(
        private var view: FieldView?,
        private var stepText: String
) : FieldPresenter {

    private var model: FieldModel? = FieldModelImpl(
            view!!.getPlayerOne(),
            view!!.getPlayerTwo()
    )

    init {
        model?.setStepText(stepText)
        view!!.setStep(model!!.getStep())
        view!!.setVictoryOne(model!!.getScorePlayerOne())
        view!!.setVictoryTwo(model!!.getScorePlayerTwo())
    }

    private fun clearField(){
        view!!.clearField()
        model!!.createLists()
        model!!.setStep(true)
    }

    override fun onClickBox(i: Int) {
        if (!model!!.getEnd() && !model!!.checkDraw()) {
            if (model!!.onClickBox(i)) {
                if (model!!.isStep()) {
                    view!!.setListBox(i, "X")
                    if (model!!.checkVictory()) {
                        view!!.textColorEnd(model!!.getBoxWinList())
                        view!!.setVictoryOne(model!!.getScorePlayerOne())
                    } else {
                        model!!.setStep(false)
                    }
                } else {
                    view!!.setListBox(i, "O")
                    if (model!!.checkVictory()) {
                        view!!.textColorEnd(model!!.getBoxWinList())
                        view!!.setVictoryTwo(model!!.getScorePlayerTwo())
                    } else {
                        model!!.setStep(true)
                    }
                }
                view!!.setStep(model!!.getStep())
            }
        } else {
            view!!.textColor()
            clearField()
        }
    }

    override fun onClickBack() {
        view!!.finishActivity()
    }

    override fun onClickClear() {
        clearField()
        view!!.setStep(model!!.getStep())
        model!!.clearScore()
        view!!.setVictoryOne(model!!.getScorePlayerOne())
        view!!.setVictoryTwo(model!!.getScorePlayerTwo())
        view!!.textColor()
    }

    override fun onDestroy() {
        view = null
        model = null
    }
}