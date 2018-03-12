package cat.ycatapp.xandone.ui.info;

import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

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

    @Override
    public int setLayout() {
        return R.layout.frag_info_layout;
    }

    @Override
    public void initData() {
        super.initData();
        if (UserInfoCache.isLogin()) {
            refreshData();
        }
    }

    @Override
    protected void initInject() {
//        getFragmentComponent().inject(this);
    }

    @OnClick({R.id.frag_info_login, R.id.frag_info_icon_ll})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.frag_info_login:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
            case R.id.frag_info_icon_ll:
                startActivity(new Intent(mActivity, PersonalActivity.class));
                break;
        }
    }

    /**
     * 刷新个人信息
     */
    public void refreshData() {
        frag_info_login_ll.setVisibility(View.GONE);
        frag_info_icon_ll.setVisibility(View.VISIBLE);
    }

}
