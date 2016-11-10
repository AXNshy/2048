package zqx.com.a2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import zqx.com.a2048.view.GameLayout;

public class MainActivity extends AppCompatActivity {

    Random random;
    @BindView(R.id.tv_score)
    TextView Score;
    @BindView(R.id.iv_menu)
    ImageView menu;
    @BindView(R.id.l_game)
    GameLayout Lgame;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }


}
