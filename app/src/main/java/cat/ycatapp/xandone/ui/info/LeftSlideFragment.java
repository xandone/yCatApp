package cat.ycatapp.xandone.ui.info;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.AppManager;
import cat.ycatapp.xandone.MainActivity;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.BaseFragment;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.ui.collect.CollectionActivity;
import cat.ycatapp.xandone.uitils.ToastUtils;
import cat.ycatapp.xandone.uitils.imgload.XGlide;

public class LeftSlideFragment extends BaseFragment {
    @BindView(R.id.menu_1)
    LinearLayout menu_1;
    @BindView(R.id.user_icon_iv)
    ImageView user_icon_iv;
    @BindView(R.id.user_name_tv)
    TextView user_name_tv;

    private MainActivity mActivity;
    private MyBroadCast mMyBroadCast;

    public static final String ACTION_LEFT_SLIDE_FRAGMENT = "action_left_slide_fragment";
    public static final String KEY_LOAD_USER_ICON = "key_load_user_icon";

    public static final int VALUE_LOAD_USER_ICON = 1;

    @Override
    public int setLayout() {
        return R.layout.frag_leftslide_layout;
    }

    @Override
    public void initData() {
        mActivity = (MainActivity) getActivity();
        loadUserInfo();

        mMyBroadCast = new MyBroadCast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_LEFT_SLIDE_FRAGMENT);
        mActivity.registerReceiver(mMyBroadCast, filter);
    }

    @Override
    protected void lazyLoadData() {

    }

    public void loadUserInfo() {
        if (UserInfoCache.isLogin()) {
            XGlide.loadImage(Glide.with(this), user_icon_iv, UserInfoCache.getUserBean().getIconUrl());
            user_name_tv.setText(UserInfoCache.getUserBean().getNickName());
        } else {
            user_icon_iv.setImageResource(R.drawable.df_head);
            user_name_tv.setText(getString(R.string.s_name));
        }
    }

    @OnClick({R.id.menu_1, R.id.menu_2, R.id.menu_3, R.id.foot_menu_set, R.id.foot_menu_exit, R.id.user_icon_iv})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.menu_1:
//                mActivity.startActivity(new Intent(mActivity, ChooseCityActivity.class));
                break;
            case R.id.menu_2:
                if (!UserInfoCache.isLogin()) {
                    ToastUtils.showShort(R.string.s_no_login);
                    break;
                }
                startActivity(new Intent(mActivity, CollectionActivity.class));
                break;
            case R.id.menu_3:
                new android.app.AlertDialog.Builder(mActivity)
                        .setTitle("Author")
                        .setMessage(Html.fromHtml("by xandone\t<a href='https://github.com/xandone/yCatApp'>github</a>"))
                        .setCancelable(true)
                        .show();
                break;
            case R.id.foot_menu_set:
//                mActivity.startActivity(new Intent(mActivity, SetActivity.class));
                break;
            case R.id.foot_menu_exit:
                AppManager.newInstance().finishAllActivity();
                break;
            case R.id.user_icon_iv:
                startActivity(new Intent(mActivity, InfoActivity.class));
                break;
        }
        setOnCloseDrawerLayout(mActivity);
    }

    public interface OnCloseDrawerLayout {
        void OnClose();
    }

    public void setOnCloseDrawerLayout(OnCloseDrawerLayout onCloseDrawerLayout) {
        onCloseDrawerLayout.OnClose();
    }

    @Override
    public void onDestroy() {
        if (mActivity != null) {
            mActivity.unregisterReceiver(mMyBroadCast);
        }
        super.onDestroy();
    }

    class MyBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            int key = intent.getIntExtra(KEY_LOAD_USER_ICON, 0);
            if (key == VALUE_LOAD_USER_ICON) {
                loadUserInfo();
            }
        }
    }

}