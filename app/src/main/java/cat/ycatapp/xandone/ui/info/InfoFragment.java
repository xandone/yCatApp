package cat.ycatapp.xandone.ui.info;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.ui.login.LoginActivity;
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
    protected void initInject() {
//        getFragmentComponent().inject(this);
    }

    @OnClick({R.id.frag_info_login})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.frag_info_login:
                startActivity(new Intent(mActivity, LoginActivity.class));
                break;
        }
    }

    /**
     * 刷新个人信息
     */
    public void refreshData() {
        frag_info_login_ll.setVisibility(View.GONE);
        frag_info_icon_ll.setVisibility(View.VISIBLE);
        SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
        String info = spUtils.getString(Constants.USER_INFO_KEY);
        Log.d("yandone", info);
    }

}
