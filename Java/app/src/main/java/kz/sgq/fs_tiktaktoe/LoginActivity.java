package kz.sgq.fs_tiktaktoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.sgq.fs_tiktaktoe.ui.FieldActivity;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.playerOne)
    EditText playerOne;

    @BindView(R.id.playerTwo)
    EditText playerTwo;

    private Intent field;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        field = new Intent(this, FieldActivity.class);
    }

    @OnClick(R.id.play)
    public void play() {
        if (!TextUtils.isEmpty(playerOne.getText()) &&
                !TextUtils.isEmpty(playerTwo.getText())) {
            field.putExtra("playerOne", playerOne.getText().toString())
                    .putExtra("playerTwo", playerTwo.getText().toString());
            startActivity(field);
        } else {
            if (TextUtils.isEmpty(playerOne.getText()))
                playerOne.setError(getString(R.string.enterOne));
            if (TextUtils.isEmpty(playerTwo.getText()))
                playerTwo.setError(getString(R.string.enterTwo));
        }
    }
}
