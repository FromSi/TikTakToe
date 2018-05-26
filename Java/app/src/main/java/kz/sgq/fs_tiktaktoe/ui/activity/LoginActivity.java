package kz.sgq.fs_tiktaktoe.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.sgq.fs_tiktaktoe.R;
import kz.sgq.fs_tiktaktoe.utilit.PlayerNull;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.playerOne)
    EditText playerOne;

    @BindView(R.id.playerTwo)
    EditText playerTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.playOne, R.id.playTwo})
    public void play(View view) {
        switch (view.getId()) {
            case R.id.playOne:
                startIntent(new Intent(this, FieldActivity.class)
                        .putExtra("mode", true));
                break;
            case R.id.playTwo:
                startIntent(new Intent(this, FieldActivity.class)
                        .putExtra("mode", false));
                break;
        }
    }

    private void startIntent(Intent intent) {
        if (!TextUtils.isEmpty(playerOne.getText()) &&
                !TextUtils.isEmpty(playerTwo.getText())) {
            intent.putExtra("playerOne", playerOne.getText().toString())
                    .putExtra("playerTwo", playerTwo.getText().toString());
            startActivity(intent);
        } else {
            if (TextUtils.isEmpty(playerOne.getText())) nullName(PlayerNull.PLAYER_ONE);
            if (TextUtils.isEmpty(playerTwo.getText())) nullName(PlayerNull.PLAYER_TWO);
        }
    }

    private void nullName(PlayerNull playerNull) {
        switch (playerNull) {
            case PLAYER_ONE:
                playerOne.setError(getString(R.string.enterOne));
                break;
            case PLAYER_TWO:
                playerTwo.setError(getString(R.string.enterTwo));
                break;
        }
    }
}
