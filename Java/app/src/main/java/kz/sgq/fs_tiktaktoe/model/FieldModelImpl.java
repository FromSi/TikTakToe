package kz.sgq.fs_tiktaktoe.model;

import java.util.ArrayList;
import java.util.List;

public class FieldModelImpl extends FieldModelBase implements FieldModel {

    private String playerOne;
    private String playerTwo;
    private int scorePlayerOne;
    private int scorePlayerTwo;
    private List<Integer> listBoxOne;
    private List<Integer> listBoxTwo;
    private boolean step = true;

    public FieldModelImpl(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        scorePlayerOne = 0;
        scorePlayerTwo = 0;
        createLists();
    }

    @Override
    public String getScorePlayerOne() {
        return playerOne + " " + scorePlayerOne;
    }

    @Override
    public String getScorePlayerTwo() {
        return scorePlayerTwo + " " + playerTwo;
    }

    @Override
    public String getStep() {
        if (step)
            return playerOne;
        else
            return playerTwo;
    }

    @Override
    public boolean onClickBox(int i) {
        if (step) {
            if (listBoxOne.get(i) == -1 &&
                    listBoxTwo.get(i) == -1) {
                listBoxOne.set(i, i);
                return true;
            }
        } else {
            if (listBoxOne.get(i) == -1 &&
                    listBoxTwo.get(i) == -1) {
                listBoxTwo.set(i, i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isStep() {
        return step;
    }

    @Override
    public boolean checkVictory() {
        if (isStep()) {
            if (checkBox(listBoxOne)){
                scorePlayerOne++;
                return true;
            } else
                return false;
        } else {
            if (checkBox(listBoxTwo)){
                scorePlayerTwo++;
                return true;
            } else
                return false;
        }
    }

    @Override
    public void setStep(boolean b) {
        step = b;
    }

    @Override
    public void createLists() {
        listBoxOne = new ArrayList<>();
        listBoxTwo = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listBoxOne.add(-1);
            listBoxTwo.add(-1);
        }
    }

    @Override
    public boolean checkDraw() {
        for (int i = 0; i < 8; i++) {
            if (listBoxOne.get(i) == -1 &&
                    listBoxTwo.get(i) == -1)
                return false;
        }
        return true;
    }

    @Override
    public void clearScore() {
        scorePlayerOne = 0;
        scorePlayerTwo = 0;
    }

    private boolean checkBox(List<Integer> list) {
        for (int i = 0; i < 8; i++) {
            boolean boxOne = false;
            boolean boxTwo = false;
            boolean boxThree = false;
            for (int j = 0; j < 9; j++) {
                if (getAnswer(i, 0) == list.get(j)) boxOne = true;
                if (getAnswer(i, 1) == list.get(j)) boxTwo = true;
                if (getAnswer(i, 2) == list.get(j)) boxThree = true;
            }
            if (boxOne && boxTwo && boxThree) {
                return true;
            }
        }
        return false;
    }


}
