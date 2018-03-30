package kz.sgq.fs_tiktaktoe.ui;

public interface FieldView {
    String getPlayerOne();
    String getPlayerTwo();
    void setStep(String text);
    void setVictoryOne(String victory);
    void setVictoryTwo(String victory);
    void showDialog(String text);
    void clearField();
    void setListBox(int i, String text);
    void finishActivity();
}
