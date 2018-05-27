package kz.sgq.fs_tiktaktoe.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import kz.sgq.fs_tiktaktoe.mvp.model.interfaces.FieldModel;
import kz.sgq.fs_tiktaktoe.mvp.model.FieldModelImpl;
import kz.sgq.fs_tiktaktoe.mvp.view.FieldView;

@InjectViewState
public class FieldPresenter extends MvpPresenter<FieldView> {
    private FieldModel model;

    public FieldPresenter(boolean mode, String stepText,
                          String playerOne, String playerTwo) {
        model = new FieldModelImpl(playerOne, playerTwo);
        init(mode, stepText);
    }

    private void init(boolean mode, String stepText) {
        model.setMode(mode);
        model.setStepText(stepText);
        getViewState().setStep(model.getStep());
        getViewState().setVictoryOne(model.getScorePlayerOne());
        getViewState().setVictoryTwo(model.getScorePlayerTwo());
    }


    public void onClickBox(int i) {
        if (model.isMode())
            onClickOne(i);
        else
            onClickTwo(i);
    }

    private void onClickOne(int i) {
        if (!model.getEnd() &&
                !model.checkDraw()) {
            if (model.onClickBox(i)) {
                getViewState().setListBox(i, "X");
                if (!model.checkVictory()) {
                    model.setStep(false);
                    if (model.checkDraw()) {
                        return;
                    }
                    int autoClick = model.stepBot();
                    model.onClickBox(autoClick);
                    getViewState().setListBox(autoClick, "O");
                    if (!model.checkVictory()) {
                        model.setStep(true);
                    } else {
                        getViewState().textColorEnd(model.getBoxWinList());
                        getViewState().setVictoryTwo(model.getScorePlayerTwo());
                    }
                } else
                    getViewState().textColorEnd(model.getBoxWinList());
                getViewState().setVictoryOne(model.getScorePlayerOne());
            }
        } else {
            getViewState().textColor();
            clearField();
        }
    }

    private void onClickTwo(int i) {
        if (!model.getEnd() &&
                !model.checkDraw()) {
            if (model.onClickBox(i)) {
                if (model.isStep()) {
                    getViewState().setListBox(i, "X");
                    if (model.checkVictory()) {
                        getViewState().textColorEnd(model.getBoxWinList());
                        getViewState().setVictoryOne(model.getScorePlayerOne());
                    } else {
                        model.setStep(false);
                    }
                } else {
                    getViewState().setListBox(i, "O");
                    if (model.checkVictory()) {
                        getViewState().textColorEnd(model.getBoxWinList());
                        getViewState().setVictoryTwo(model.getScorePlayerTwo());
                    } else {
                        model.setStep(true);
                    }
                }
                getViewState().setStep(model.getStep());
            }
        } else {
            getViewState().textColor();
            clearField();
        }
    }

    public void onClickBack() {
        getViewState().finishActivity();
    }


    public void onClickClear() {
        clearField();
        getViewState().setStep(model.getStep());
        model.clearScore();
        getViewState().setVictoryOne(model.getScorePlayerOne());
        getViewState().setVictoryTwo(model.getScorePlayerTwo());
        getViewState().textColor();
    }

    private void clearField() {
        getViewState().clearField();
        model.createLists();
        model.setStep(true);
    }

}
