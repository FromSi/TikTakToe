package kz.sgq.fs_tiktaktoe.mvp.view;

import com.arellomobile.mvp.MvpView;

import java.util.List;

public interface FieldView extends MvpView {
    void setListBox(int i, String text);
    void setStep(String text);
    void setVictoryOne(String victory);
    void setVictoryTwo(String victory);
    void textColorEnd(List<Integer> list);
    void clearField();
    void finishActivity();
    void textColor();
}
