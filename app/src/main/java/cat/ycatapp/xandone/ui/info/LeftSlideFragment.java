package cat.ycatapp.xandone.ui.info;


import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.AppManager;
import cat.ycatapp.xandone.MainActivity;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.BaseFragment;
import cat.ycatapp.xandone.uitils.ToastUtils;

public class LeftSlideFragment extends BaseFragment {
    @BindView(R.id.menu_1)
    LinearLayout menu_1;

    private MainActivity mActivity;

    @Override
    public int setLayout() {
        return R.layout.frag_leftslide_layout;
    }

    @Override
    public void initData() {
        mActivity = (MainActivity) getActivity();
    }

    @Override
    protected void lazyLoadData() {

    }

    @OnClick({R.id.menu_1, R.id.menu_2, R.id.foot_menu_set, R.id.foot_menu_exit, R.id.user_icon_iv})
    public void click(View v) {
        switch (v.getId()) {
            case R.id.menu_1:
//                mActivity.startActivity(new Intent(mActivity, ChooseCityActivity.class));
                break;
            case R.id.menu_2:
                ToastUtils.showShort("by xandone...");
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

}