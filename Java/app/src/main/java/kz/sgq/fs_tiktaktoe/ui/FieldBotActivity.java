package kz.sgq.fs_tiktaktoe.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kz.sgq.fs_tiktaktoe.R;
import kz.sgq.fs_tiktaktoe.presenter.FieldBotPresenterImpl;
import kz.sgq.fs_tiktaktoe.presenter.FieldPresenter;

public class FieldBotActivity extends AppCompatActivity implements FieldView {
    private FieldPresenter presenter;

    @BindView(R.id.playerOne)
    TextView playerOne;

    @BindView(R.id.playerTwo)
    TextView playerTwo;

    @BindViews({R.id.box11, R.id.box12, R.id.box13,
            R.id.box21, R.id.box22, R.id.box23,
            R.id.box31, R.id.box32, R.id.box33})
    List<TextView> listBox;

    @BindView(R.id.step)
    TextView step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        presenter = new FieldBotPresenterImpl(this,
                getResources().getString(R.string.step));
    }

    @OnClick({R.id.box11, R.id.box12, R.id.box13,
            R.id.box21, R.id.box22, R.id.box23,
            R.id.box31, R.id.box32, R.id.box33,
            R.id.back, R.id.clear})
    public void onClickBox(View view) {
        switch (view.getId()) {
            case R.id.box11:
                presenter.onClickBox(0);
                break;
            case R.id.box12:
                presenter.onClickBox(1);
                break;
            case R.id.box13:
                presenter.onClickBox(2);
                break;
            case R.id.box21:
                presenter.onClickBox(3);
                break;
            case R.id.box22:
                presenter.onClickBox(4);
                break;
            case R.id.box23:
                presenter.onClickBox(5);
                break;
            case R.id.box31:
                presenter.onClickBox(6);
                break;
            case R.id.box32:
                presenter.onClickBox(7);
                break;
            case R.id.box33:
                presenter.onClickBox(8);
                break;
            case R.id.back:
                presenter.onClickBack();
                break;
            case R.id.clear:
                presenter.onClickClear();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public String getPlayerOne() {
        return getIntent().getStringExtra("playerOne");
    }

    @Override
    public String getPlayerTwo() {
        return getIntent().getStringExtra("playerTwo");
    }

    @Override
    public void setStep(String text) {
        step.setText(text);
    }

    @Override
    public void setVictoryOne(String victory) {
        playerOne.setText(victory);
    }

    @Override
    public void setVictoryTwo(String victory) {
        playerTwo.setText(victory);
    }

    @Override
    public void clearField() {
        for (int i = 0; i < listBox.size(); i++) {
            listBox.get(i).setText(null);
        }
    }

    @Override
    public void setListBox(int i, String text) {
        listBox.get(i).setText(text);
    }

    @Override
    public void finishActivity() {
        finish();
    }

    @Override
    public void textColor() {
        for (int i = 0; i < listBox.size(); i++) {
            listBox.get(i).setTextColor(getResources().
                    getColor(R.color.colorText));
        }
    }

    @Override
    public void textColorEnd(List<Integer> list) {
        for (int i = 0; i < listBox.size(); i++) {
            if (list.get(0) != i &&
                    list.get(1) != i &&
                    list.get(2) != i)
                listBox.get(i).setTextColor(getResources().
                        getColor(R.color.colorAccent));
        }
    }
}
