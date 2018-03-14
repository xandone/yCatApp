package cat.ycatapp.xandone.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import cat.ycatapp.xandone.AppManager;

/**
 * author: xandone
 * created on: 2018/3/5 15:30
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBefore();
        setContentView(setLayout());
        ButterKnife.bind(this);
        AppManager.newInstance().addActivivty(this);
        initData();
    }

    public void doBefore() {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public abstract int setLayout();

    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.newInstance().removectivity(this);
    }

    @Override
    public void showMsg(String msg, int loadStatus) {

    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    @Override
    public void stateMain() {

    }

    public void setToolBar(Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(null);
    }
}
