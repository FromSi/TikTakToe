package kz.sgq.fs_tiktaktoe.presenter;

import kz.sgq.fs_tiktaktoe.model.FieldModel;
import kz.sgq.fs_tiktaktoe.model.FieldModelImpl;
import kz.sgq.fs_tiktaktoe.ui.FieldView;

public class FieldPresenterImlp implements FieldPresenter {
    private FieldView view;
    private FieldModel model;

    public FieldPresenterImlp(FieldView view) {
        this.view = view;
        init();
    }

    private void init() {
        model = new FieldModelImpl(view.getPlayerOne(),
                view.getPlayerTwo());
        view.setStep("Step " + model.getStep());
        view.setVictoryOne(model.getScorePlayerOne());
        view.setVictoryTwo(model.getScorePlayerTwo());
    }

    @Override
    public void onClickBox(int i) {
        if (model.onClickBox(i)) {
            if (model.isStep()) {
                view.setListBox(i, "X");
                if (model.checkVictory()) {
                    view.setVictoryOne(model.getScorePlayerOne());
                    view.showDialog("Winner " + model.getStep());
                    clearField();
                } else {
                    model.setStep(false);
                }
            } else {
                view.setListBox(i, "O");
                if (model.checkVictory()) {
                    view.setVictoryTwo(model.getScorePlayerTwo());
                    view.showDialog("Winner " + model.getStep());
                    clearField();
                } else {
                    model.setStep(true);

                }
            }
            checkDraw();
            view.setStep("Step " + model.getStep());
        }
    }

    @Override
    public void onClickBack() {
        view.finishActivity();
    }

    @Override
    public void onClickClear() {
        clearField();
        view.setStep("Step " + model.getStep());
        model.clearScore();
        view.setVictoryOne(model.getScorePlayerOne());
        view.setVictoryTwo(model.getScorePlayerTwo());
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

    private void checkDraw(){
        if (model.checkDraw()){
            view.showDialog("Dead Heat");
            clearField();
        }
    }

}
