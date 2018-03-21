package cat.ycatapp.xandone.ui.info;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.ui.login.LoginActivity;
import cat.ycatapp.xandone.ui.personal.PersonalActivity;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.SPUtils;

/**
 * author: xandone
 * created on: 2018/3/6 13:35
 */

public class InfoFragment extends RxBaseFragment {
    @BindView(R.id.frag_info_login_ll)
    LinearLayout frag_info_login_ll;
    @BindView(R.id.frag_info_icon_ll)
    LinearLayout frag_info_icon_ll;
    @BindView(R.id.frag_info_nick)
    TextView frag_info_nick;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    public int setLayout() {
        return R.layout.frag_info_layout;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolBar, getString(R.string.x_personal_title));
        if (UserInfoCache.isLogin()) {
            showUserInfo();
        }
    }

    @Override
    protected void initInject() {
//        getFragmentComponent().inject(this);
    }

    @OnClick({R.id.frag_info_login, R.id.frag_info_out})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.frag_info_login:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
            case R.id.frag_info_out:

                showDialog("是否退出登录", "确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SPUtils.getInstance(Constants.USER_INFO_NAME).remove(Constants.USER_INFO_KEY);
                        UserInfoCache.setLogin(false);
                        UserInfoCache.setUserBean(null);
                        logout();

                    }
                }, "取消", null);
                break;
        }
    }

    /**
     * 显示个人信息
     */
    public void showUserInfo() {
        frag_info_login_ll.setVisibility(View.GONE);
        frag_info_icon_ll.setVisibility(View.VISIBLE);
        frag_info_nick.setText(UserInfoCache.getUserBean().getNickName());
    }

    /**
     * 退出登录清除用户信息
     */
    public void logout() {
        frag_info_login_ll.setVisibility(View.VISIBLE);
        frag_info_icon_ll.setVisibility(View.GONE);
        frag_info_nick.setText("");
    }


}
