package kz.sgq.fs_tiktaktoe.model;

import java.util.List;

public interface FieldModel {
    String getScorePlayerOne();
    String getScorePlayerTwo();
    String getStep();
    boolean onClickBox(int i);
    boolean isStep();
    boolean checkVictory();
    void setStep(boolean b);
    void createLists();
    boolean checkDraw();
    void clearScore();
}
