package kz.sgq.fs_tiktaktoe.ui;

import java.util.List;

public interface FieldView {
    String getPlayerOne();
    String getPlayerTwo();
    void setStep(String text);
    void setVictoryOne(String victory);
    void setVictoryTwo(String victory);
    void clearField();
    void setListBox(int i, String text);
    void finishActivity();
    void textColor();
    void textColorEnd(List<Integer> list);
}
