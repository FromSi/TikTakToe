package kz.sgq.kotlintictactoe.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import kotlinx.android.synthetic.main.activity_login.*
import kz.sgq.kotlintictactoe.R
import kz.sgq.kotlintictactoe.utils.PlayerNull

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onClick(view: View) {
        when (view) {
            playOne -> startIntent(Intent(this, FieldActivity::class.java)
                    .putExtra("mode", true))
            playTwo -> startIntent(Intent(this, FieldActivity::class.java)
                    .putExtra("mode", false))
        }
    }

    private fun startIntent(intent: Intent) {
        if (!TextUtils.isEmpty(playerOne.text) &&
                !TextUtils.isEmpty(playerTwo.text)){
            intent.putExtra("playerOne", playerOne.text.toString())
                    .putExtra("playerTwo", playerTwo.text.toString())
            startActivity(intent)
        } else {
            if (TextUtils.isEmpty(playerOne.text))
                nullName(PlayerNull.PLAYER_ONE)
            if (TextUtils.isEmpty(playerTwo.text))
                nullName(PlayerNull.PLAYER_TWO)
        }
    }

    private fun nullName(playerNull: PlayerNull) {
        when (playerNull) {
            PlayerNull.PLAYER_ONE -> playerOne.error = getString(R.string.enterOne)
            PlayerNull.PLAYER_TWO -> playerTwo.error = getString(R.string.enterTwo)
        }
    }
}
