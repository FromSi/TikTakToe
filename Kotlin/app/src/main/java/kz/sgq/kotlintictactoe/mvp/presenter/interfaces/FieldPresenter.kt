package kz.sgq.kotlintictactoe.mvp.presenter.interfaces

interface FieldPresenter {
    fun onClickBox(i: Int)
    fun onClickBack()
    fun onClickClear()
    fun onDestroy()
}