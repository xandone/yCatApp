package cat.ycatapp.xandone.ui.info;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;
import cat.ycatapp.xandone.ui.login.LoginActivity;

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

    }

}
