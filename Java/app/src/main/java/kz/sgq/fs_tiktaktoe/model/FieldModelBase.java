package kz.sgq.fs_tiktaktoe.model;

public class FieldModelBase {
    private int[][] answerArr = {{0, 1, 2},
            {0, 3, 6},
            {0, 4, 8},
            {1, 4, 7},
            {2, 4, 6},
            {2, 5, 8},
            {3, 4, 5},
            {6, 7, 8}};

    public int getAnswer(int i, int j) {
        return answerArr[i][j];
    }
}
