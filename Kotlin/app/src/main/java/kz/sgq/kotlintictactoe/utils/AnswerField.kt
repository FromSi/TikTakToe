package kz.sgq.kotlintictactoe.utils

object AnswerField {
    val answerArr = arrayOf(intArrayOf(0, 1, 2),
            intArrayOf(0, 3, 6),
            intArrayOf(0, 4, 8),
            intArrayOf(1, 4, 7),
            intArrayOf(2, 4, 6),
            intArrayOf(2, 5, 8),
            intArrayOf(3, 4, 5),
            intArrayOf(6, 7, 8))

    fun getAnswer(i: Int, j: Int) = answerArr[i][j]
}