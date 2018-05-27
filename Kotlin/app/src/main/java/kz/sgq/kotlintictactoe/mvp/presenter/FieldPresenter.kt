package kz.sgq.kotlintictactoe.mvp.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import kz.sgq.kotlintictactoe.mvp.model.FieldModelImpl
import kz.sgq.kotlintictactoe.mvp.model.interfaces.FieldModel
import kz.sgq.kotlintictactoe.mvp.view.FieldView

@InjectViewState
class FieldPresenter(
        mode: Boolean,
        stepText: String,
        playerOne: String,
        playerTwo: String
) : MvpPresenter<FieldView>() {

    private var model: FieldModel = FieldModelImpl(playerOne, playerTwo)

    init {
        model.setMode(mode)
        model.setStepText(stepText)
        viewState.setStep(model.getStep())
        viewState.setVictoryOne(model.getScorePlayerOne())
        viewState.setVictoryTwo(model.getScorePlayerTwo())
    }

    private fun clearField() {
        viewState.clearField()
        model.createLists()
        model.setStep(true)
    }

    fun onClickBox(i: Int) {
        if (model.isMode())
            onClickOne(i)
        else
            onClickTwo(i)
    }

    private fun onClickOne(i: Int) {
        if (!model.getEnd() && !model.checkDraw()) {
            if (model.onClickBox(i)) {
                viewState.setListBox(i, "X")
                if (!model.checkVictory()) {
                    model.setStep(false)
                    if (model.checkDraw()) {
                        return
                    }
                    val autoClick = model.stepBot()
                    model.onClickBox(autoClick)
                    viewState.setListBox(autoClick, "O")
                    if (!model.checkVictory()) {
                        model.setStep(true)
                    } else {
                        viewState.textColorEnd(model.getBoxWinList())
                        viewState.setVictoryTwo(model.getScorePlayerTwo())
                    }
                } else
                    viewState.textColorEnd(model.getBoxWinList())
                viewState.setVictoryOne(model.getScorePlayerOne())
            }
        } else {
            viewState.textColor()
            clearField()
        }
    }

    private fun onClickTwo(i: Int) {
        if (!model.getEnd() && !model.checkDraw()) {
            if (model.onClickBox(i)) {
                if (model.isStep()) {
                    viewState.setListBox(i, "X")
                    if (model.checkVictory()) {
                        viewState.textColorEnd(model.getBoxWinList())
                        viewState.setVictoryOne(model.getScorePlayerOne())
                    } else {
                        model.setStep(false)
                    }
                } else {
                    viewState.setListBox(i, "O")
                    if (model.checkVictory()) {
                        viewState.textColorEnd(model.getBoxWinList())
                        viewState.setVictoryTwo(model.getScorePlayerTwo())
                    } else {
                        model.setStep(true)
                    }
                }
                viewState.setStep(model.getStep())
            }
        } else {
            viewState.textColor()
            clearField()
        }
    }

    fun onClickBack() {
        viewState.finishActivity()
    }

    fun onClickClear() {
        clearField()
        viewState.setStep(model.getStep())
        model.clearScore()
        viewState.setVictoryOne(model.getScorePlayerOne())
        viewState.setVictoryTwo(model.getScorePlayerTwo())
        viewState.textColor()
    }
}