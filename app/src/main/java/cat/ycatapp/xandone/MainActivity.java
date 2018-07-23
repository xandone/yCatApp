package cat.ycatapp.xandone;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.ui.bar.ImageFragment;
import cat.ycatapp.xandone.ui.info.LeftSlideFragment;
import cat.ycatapp.xandone.ui.joke.JokeFragment;
import cat.ycatapp.xandone.ui.video.VideoListFragment;
import cat.ycatapp.xandone.uitils.ToastUtils;

public class MainActivity extends RxBaseActivity implements LeftSlideFragment.OnCloseDrawerLayout {
    @BindView(R.id.main_foot_rg)
    RadioGroup main_foot_rg;
    @BindView(R.id.drawerlayout)
    DrawerLayout drawerlayout;

    private int mFragIndex;
    private Fragment mCurrentFrag;
    private List<Fragment> fragList;
    private boolean isState = true;

    private VideoListFragment mInfoFragment;
    private JokeFragment mJokeFragment;
    private ImageFragment mImageFragment;
    private LeftSlideFragment mLeftSlideFragment;

    public static final String X_USER_RELOAD = MainActivity.class.getName() + "_USER_RELOAD";
    public static final int USER_LOGIN = 1;
    public static final int USER_REGIST = 2;
    public static final int USER_LOGOUT = 3;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initInject() {
    }

    @Override
    public void initData() {
        super.initData();

        mFragIndex = 0;
        mJokeFragment = new JokeFragment();
        mImageFragment = new ImageFragment();
        mInfoFragment = new VideoListFragment();

        fragList = new ArrayList<Fragment>(Arrays.asList(mJokeFragment, mImageFragment, mInfoFragment));
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

        mLeftSlideFragment = (LeftSlideFragment) getSupportFragmentManager().findFragmentById(R.id.leftSlide_frag);
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
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent == null) {
            return;
        }
        int action = intent.getIntExtra(X_USER_RELOAD, 0);
        switch (action) {
            case USER_LOGIN:
                if (mLeftSlideFragment != null) {
                    mLeftSlideFragment.loadUserInfo();
                }
                break;
            case USER_REGIST:
                if (mLeftSlideFragment != null) {
                    mLeftSlideFragment.loadUserInfo();
                }
                break;
            case USER_LOGOUT:
                if (mLeftSlideFragment != null) {
                    mLeftSlideFragment.loadUserInfo();
                }
                break;
        }
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

    @Override
    public void OnClose() {
        drawerlayout.closeDrawers();
    }
}
