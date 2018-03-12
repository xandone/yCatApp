package cat.ycatapp.xandone.ui.login;

import android.content.Intent;
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
import cat.ycatapp.xandone.ui.regist.RegistActivity;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.SPUtils;
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

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.act_login_layout;
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
    public void showContent(BaseResponse<List<LoginBean>> baseResponse) {
        if (baseResponse != null) {
            if ("1".equals(baseResponse.getCode()) && baseResponse.getDataList() != null
                    && !baseResponse.getDataList().isEmpty()) {
                UserInfoCache.setLogin(true);
                LoginBean loginBean = baseResponse.getDataList().get(0);
                String info = GsonUtil.objToJson(loginBean);
                SPUtils spUtils = SPUtils.getInstance(Constants.USER_INFO_NAME);
                spUtils.put(Constants.USER_INFO_KEY, info);
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.X_USER_RELOAD, MainActivity.USER_REGIST);
                startActivity(intent);
            } else if (!TextUtils.isEmpty(baseResponse.getMsg())) {
                ToastUtils.showShort(baseResponse.getMsg());
            }
        }
    }
}