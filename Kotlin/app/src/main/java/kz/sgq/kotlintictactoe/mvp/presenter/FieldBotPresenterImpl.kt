package kz.sgq.kotlintictactoe.mvp.presenter

import kz.sgq.kotlintictactoe.mvp.model.FieldModelImpl
import kz.sgq.kotlintictactoe.mvp.model.interfaces.FieldModel
import kz.sgq.kotlintictactoe.mvp.presenter.interfaces.FieldPresenter
import kz.sgq.kotlintictactoe.mvp.view.FieldView

class FieldBotPresenterImpl(
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

    private fun clearField() {
        view!!.clearField()
        model!!.createLists()
        model!!.setStep(true)
    }

    override fun onClickBox(i: Int) {
        if (!model!!.getEnd() && !model!!.checkDraw()) {
            if (model!!.onClickBox(i)) {
                view!!.setListBox(i, "X")
                if (!model!!.checkVictory()) {
                    model!!.setStep(false)
                    if (model!!.checkDraw()) {
                        return
                    }
                    val autoClick = model!!.stepBot()
                    model!!.onClickBox(autoClick)
                    view!!.setListBox(autoClick, "O")
                    if (!model!!.checkVictory()) {
                        model!!.setStep(true)
                    } else {
                        view!!.textColorEnd(model!!.getBoxWinList())
                        view!!.setVictoryTwo(model!!.getScorePlayerTwo())
                    }
                } else
                    view!!.textColorEnd(model!!.getBoxWinList())
                view!!.setVictoryOne(model!!.getScorePlayerOne())
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