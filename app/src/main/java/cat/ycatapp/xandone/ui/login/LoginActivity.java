package cat.ycatapp.xandone.ui.login;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.MainActivity;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.LoginBean;
import cat.ycatapp.xandone.model.bean.UserBean;
import cat.ycatapp.xandone.model.bean.UserPsw;
import cat.ycatapp.xandone.ui.regist.RegistActivity;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.SPUtils;
import cat.ycatapp.xandone.uitils.SimpleUtils;
import cat.ycatapp.xandone.uitils.ToastUtils;
import cat.ycatapp.xandone.uitils.XString;

/**
 * author: xandone
 * created on: 2018/3/6 15:09
 */

public class LoginActivity extends RxBaseActivity<LoginPresenter> implements LoginContact.View {
    @BindView(R.id.act_login_et_email)
    EditText act_login_et_email;
    @BindView(R.id.act_login_et_psw)
    EditText act_login_et_psw;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.act_login_layout;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolBar, getTitle().toString());
    }

    @OnClick({R.id.act_login_regist, R.id.act_login_btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.act_login_regist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;
            case R.id.act_login_btn:
                String email = act_login_et_email.getText().toString().trim();
                String psw = act_login_et_psw.getText().toString().trim();
                if (XString.isEmpty(email)) {
                    ToastUtils.showShort("请输入账号");
                    return;
                }
                if (XString.isEmpty(psw)) {
                    ToastUtils.showShort("请输入密码");
                    return;
                }
                mPresenter.login(email, psw);
                break;
        }
    }

    @Override
    public void showContent(BaseResponse<List<UserBean>> baseResponse) {
        if (UserInfoCache.isLogin()) {
            SimpleUtils.hideSoftInput(this);
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra(MainActivity.X_USER_RELOAD, MainActivity.USER_REGIST);
            startActivity(intent);
        } else if (!TextUtils.isEmpty(baseResponse.getMsg())) {
            ToastUtils.showShort(baseResponse.getMsg());
        } else {
            ToastUtils.showShort("服务器异常,请稍后再试");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
