package kz.sgq.fs_tiktaktoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.sgq.fs_tiktaktoe.ui.FieldActivity;
import kz.sgq.fs_tiktaktoe.ui.FieldBotActivity;

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
                startIntent(new Intent(this, FieldBotActivity.class));
                break;
            case R.id.playTwo:
                startIntent(new Intent(this, FieldActivity.class));
                break;
        }
    }

    private void startIntent(Intent intent){
        if (!TextUtils.isEmpty(playerOne.getText()) &&
                !TextUtils.isEmpty(playerTwo.getText())) {
            intent.putExtra("playerOne", playerOne.getText().toString())
                    .putExtra("playerTwo", playerTwo.getText().toString());
            startActivity(intent);
        } else {
            if (TextUtils.isEmpty(playerOne.getText()))
                playerOne.setError(getString(R.string.enterOne));
            if (TextUtils.isEmpty(playerTwo.getText()))
                playerTwo.setError(getString(R.string.enterTwo));
        }
    }
}
