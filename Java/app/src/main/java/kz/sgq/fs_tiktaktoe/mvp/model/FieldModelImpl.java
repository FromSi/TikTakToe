package kz.sgq.fs_tiktaktoe.mvp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kz.sgq.fs_tiktaktoe.mvp.model.interfaces.FieldModel;
import kz.sgq.fs_tiktaktoe.utilit.AnswerField;

public class FieldModelImpl implements FieldModel {

    private String playerOne;
    private String playerTwo;
    private int scorePlayerOne;
    private int scorePlayerTwo;
    private List<Integer> listBoxOne;
    private List<Integer> listBoxTwo;
    private List<Integer> boxWinList;
    private boolean step = true;
    private boolean end = false;
    private String stepText;

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
            return stepText + " " + playerOne;
        else
            return stepText + " " + playerTwo;
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
            if (checkBox(listBoxOne)) {
                scorePlayerOne++;
                end = true;
                return true;
            } else
                return false;
        } else {
            if (checkBox(listBoxTwo)) {
                scorePlayerTwo++;
                end = true;
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
        boxWinList = new ArrayList<>();
        listBoxOne = new ArrayList<>();
        listBoxTwo = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            listBoxOne.add(-1);
            listBoxTwo.add(-1);
        }
        end = false;
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

    @Override
    public void setStepText(String str) {
        stepText = str;
    }

    @Override
    public int stepBot() {
        int checkStepWin = checkStepBot(listBoxTwo);
        if (checkStepWin == -1) {
            int checkStepEnemy = checkStepBot(listBoxOne);
            if (checkStepEnemy == -1) {
                List<Integer> list = stepRandomBot();
                Random random = new Random(System.currentTimeMillis());
                return list.get(random.nextInt(list.size()));
            } else
                return checkStepEnemy;
        } else
            return checkStepWin;
    }

    @Override
    public boolean getEnd() {
        return end;
    }

    @Override
    public List<Integer> getBoxWinList() {
        return boxWinList;
    }

    private int checkStepBot(List<Integer> list) {
        for (int i = 0; i < 8; i++) {
            boolean boxOne = false;
            boolean boxTwo = false;
            boolean boxThree = false;
            for (int j = 0; j < list.size(); j++) {
                if (AnswerField.getAnswer(i, 0) == list.get(j)) boxOne = true;
                if (AnswerField.getAnswer(i, 1) == list.get(j)) boxTwo = true;
                if (AnswerField.getAnswer(i, 2) == list.get(j)) boxThree = true;
            }
            int answerOne = AnswerField.getAnswer(i, 2);
            int answerTwo = AnswerField.getAnswer(i, 1);
            int answerThree = AnswerField.getAnswer(i, 0);
            if (boxOne && boxTwo &&
                    listBoxOne.get(answerOne) == -1 &&
                    listBoxTwo.get(answerOne) == -1)
                return answerOne;
            if (boxOne && boxThree &&
                    listBoxOne.get(answerTwo) == -1 &&
                    listBoxTwo.get(answerTwo) == -1)
                return answerTwo;
            if (boxTwo && boxThree &&
                    listBoxOne.get(answerThree) == -1 &&
                    listBoxTwo.get(answerThree) == -1)
                return answerThree;
        }
        return -1;
    }

    private List<Integer> stepRandomBot() {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < listBoxOne.size(); j++) {
                int answerOne = AnswerField.getAnswer(i, 0);
                int answerTwo = AnswerField.getAnswer(i, 1);
                int answerThree = AnswerField.getAnswer(i, 2);
                if (answerOne != listBoxOne.get(j) &&
                        listBoxOne.get(answerOne) == -1 &&
                        listBoxTwo.get(answerOne) == -1) {
                    list.add(answerOne);
                }
                if (answerTwo != listBoxOne.get(j) &&
                        listBoxOne.get(answerTwo) == -1 &&
                        listBoxTwo.get(answerTwo) == -1) {
                    list.add(answerTwo);
                }
                if (answerThree != listBoxOne.get(j) &&
                        listBoxOne.get(answerThree) == -1 &&
                        listBoxTwo.get(answerThree) == -1) {
                    list.add(answerThree);
                }
            }
        }
        return list;
    }

    private boolean checkBox(List<Integer> list) {
        for (int i = 0; i < 8; i++) {
            boolean boxOne = false;
            boolean boxTwo = false;
            boolean boxThree = false;
            for (int j = 0; j < list.size(); j++) {
                if (AnswerField.getAnswer(i, 0) == list.get(j)) boxOne = true;
                if (AnswerField.getAnswer(i, 1) == list.get(j)) boxTwo = true;
                if (AnswerField.getAnswer(i, 2) == list.get(j)) boxThree = true;
            }
            if (boxOne && boxTwo && boxThree) {
                boxWinList.add(AnswerField.getAnswer(i, 0));
                boxWinList.add(AnswerField.getAnswer(i, 1));
                boxWinList.add(AnswerField.getAnswer(i, 2));
                return true;
            }
        }
        return false;
    }
}
