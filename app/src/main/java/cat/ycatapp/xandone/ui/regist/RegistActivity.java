package cat.ycatapp.xandone.ui.regist;

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
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.RegistBean;
import cat.ycatapp.xandone.uitils.ToastUtils;
import cat.ycatapp.xandone.uitils.XString;

/**
 * author: xandone
 * created on: 2018/3/6 20:16
 */

public class RegistActivity extends RxBaseActivity<RegistPresenter> implements RegistContact.View {
    @BindView(R.id.act_regist_et_email)
    EditText act_regist_et_email;
    @BindView(R.id.act_regist_et_psw)
    EditText act_regist_et_psw;
    @BindView(R.id.act_regist_et_nick)
    EditText act_regist_et_nick;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.act_regist_layout;
    }


    @OnClick({R.id.act_regist_btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.act_regist_btn:
                String email = act_regist_et_email.getText().toString().trim();
                String psw = act_regist_et_psw.getText().toString().trim();
                String nick = act_regist_et_nick.getText().toString().trim();
                if (XString.isEmpty(email) || !XString.isEmail(email)) {
                    ToastUtils.showShort("请输入正确的邮箱");
                    return;
                }
                if (XString.isEmpty(psw) || !XString.isPassword(psw)) {
                    ToastUtils.showShort("请输入6-16位数字/英文密码");
                    return;
                }
                if (XString.isEmpty(nick)) {
                    ToastUtils.showShort("请输入昵称");
                    return;
                }
                mPresenter.regist(email, psw, nick);
                break;
        }
    }

    @Override
    public void showContent(BaseResponse<List<RegistBean>> baseResponse) {
        if (baseResponse != null) {
            if (!TextUtils.isEmpty(baseResponse.getMsg())) {
                ToastUtils.showShort(baseResponse.getMsg());
            }
            if ("1".equals(baseResponse.getCode())) {
                UserInfoCache.setLogin(true);
                Intent intent = new Intent(RegistActivity.this, MainActivity.class);
                intent.putExtra(MainActivity.X_USER_RELOAD, MainActivity.USER_REGIST);
                startActivity(intent);
            }
        }

    }
}
