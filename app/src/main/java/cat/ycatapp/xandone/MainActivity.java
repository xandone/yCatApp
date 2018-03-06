package cat.ycatapp.xandone;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.ui.bar.BarFragment;
import cat.ycatapp.xandone.ui.info.InfoFragment;
import cat.ycatapp.xandone.ui.joke.JokeFragment;
import cat.ycatapp.xandone.uitils.ToastUtils;

public class MainActivity extends RxBaseActivity<MainPresenter> {
    @BindView(R.id.main_foot_rg)
    RadioGroup main_foot_rg;

    private int mFragIndex;
    private Fragment mCurrentFrag;
    private List<Fragment> fragList;
    private boolean isState = true;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        super.initData();

        mFragIndex = 0;
        fragList = new ArrayList<Fragment>(Arrays.asList(new JokeFragment(), new BarFragment(), new InfoFragment()));
        turnToFrag();

        main_foot_rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.main_footer_care_rb:
                        mFragIndex = 0;
                        break;
                    case R.id.main_footer_video_rb:
                        mFragIndex = 1;
                        break;
                    case R.id.main_footer_home_rb:
                        mFragIndex = 2;
                        break;
                }
                turnToFrag();
            }
        });
    }

    public void turnToFrag() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment toFragment = fragList.get(mFragIndex);
        if (mCurrentFrag != null) {
            ft.hide(mCurrentFrag);
        }
        if (toFragment.isAdded()) {
            ft.show(toFragment);
        } else {
            ft.add(R.id.main_frame, toFragment);
        }
        ft.commitAllowingStateLoss();
        mCurrentFrag = toFragment;
    }

    @Override
    public void onBackPressed() {
        if (isState) {
            isState = false;
            ToastUtils.showShort("再按一次退出");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isState = true;
                }
            }, 2000);
        } else {
            AppManager.newInstance().finishAllActivity();
        }
    }
}
