package kz.sgq.fs_tiktaktoe.mvp.model.interfaces;

import java.util.List;

public interface FieldModel {
    String getScorePlayerOne();
    String getScorePlayerTwo();
    String getStep();
    boolean isMode();
    boolean onClickBox(int i);
    boolean isStep();
    boolean checkVictory();
    boolean checkDraw();
    boolean getEnd();
    int stepBot();
    List<Integer> getBoxWinList();
    void setStep(boolean b);
    void setMode(boolean b);
    void setStepText(String str);
    void createLists();
    void clearScore();
}
