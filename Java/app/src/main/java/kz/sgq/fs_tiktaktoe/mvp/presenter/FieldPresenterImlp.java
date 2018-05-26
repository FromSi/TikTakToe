package kz.sgq.fs_tiktaktoe.mvp.presenter;

import kz.sgq.fs_tiktaktoe.mvp.model.interfaces.FieldModel;
import kz.sgq.fs_tiktaktoe.mvp.model.FieldModelImpl;
import kz.sgq.fs_tiktaktoe.mvp.presenter.interfaces.FieldPresenter;
import kz.sgq.fs_tiktaktoe.mvp.view.FieldView;

public class FieldPresenterImlp implements FieldPresenter {
    private FieldView view;
    private FieldModel model;

    public FieldPresenterImlp(FieldView view, String stepText) {
        this.view = view;
        init(stepText);
    }

    private void init(String stepText) {
        model = new FieldModelImpl(view.getPlayerOne(),
                view.getPlayerTwo());
        model.setStepText(stepText);
        view.setStep(model.getStep());
        view.setVictoryOne(model.getScorePlayerOne());
        view.setVictoryTwo(model.getScorePlayerTwo());
    }

    @Override
    public void onClickBox(int i) {
        if (!model.getEnd() &&
                !model.checkDraw()) {
            if (model.onClickBox(i)) {
                if (model.isStep()) {
                    view.setListBox(i, "X");
                    if (model.checkVictory()) {
                        view.textColorEnd(model.getBoxWinList());
                        view.setVictoryOne(model.getScorePlayerOne());
                    } else {
                        model.setStep(false);
                    }
                } else {
                    view.setListBox(i, "O");
                    if (model.checkVictory()) {
                        view.textColorEnd(model.getBoxWinList());
                        view.setVictoryTwo(model.getScorePlayerTwo());
                    } else {
                        model.setStep(true);
                    }
                }
                view.setStep(model.getStep());
            }
        } else {
            view.textColor();
            clearField();
        }
    }

    @Override
    public void onClickBack() {
        view.finishActivity();
    }

    @Override
    public void onClickClear() {
        clearField();
        view.setStep(model.getStep());
        model.clearScore();
        view.setVictoryOne(model.getScorePlayerOne());
        view.setVictoryTwo(model.getScorePlayerTwo());
        view.textColor();
    }

    @Override
    public void onDestroy() {
        view = null;
        model = null;
    }

    private void clearField() {
        view.clearField();
        model.createLists();
        model.setStep(true);
    }

}
