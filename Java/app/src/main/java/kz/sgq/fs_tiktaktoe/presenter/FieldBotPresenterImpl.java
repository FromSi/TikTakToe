package kz.sgq.fs_tiktaktoe.presenter;

import kz.sgq.fs_tiktaktoe.model.FieldModel;
import kz.sgq.fs_tiktaktoe.model.FieldModelImpl;
import kz.sgq.fs_tiktaktoe.ui.FieldView;

public class FieldBotPresenterImpl implements FieldPresenter {

    private FieldView view;
    private FieldModel model;

    public FieldBotPresenterImpl(FieldView view, String stepText) {
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
                view.setListBox(i, "X");
                if (!model.checkVictory()) {
                    model.setStep(false);
                    if (model.checkDraw()) {
                        return;
                    }
                    int autoClick = model.stepBot();
                    model.onClickBox(autoClick);
                    view.setListBox(autoClick, "O");
                    if (!model.checkVictory()) {
                        model.setStep(true);
                    } else {
                        view.textColorEnd(model.getBoxWinList());
                        view.setVictoryTwo(model.getScorePlayerTwo());
                    }
                }
                else
                    view.textColorEnd(model.getBoxWinList());
                    view.setVictoryOne(model.getScorePlayerOne());
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
