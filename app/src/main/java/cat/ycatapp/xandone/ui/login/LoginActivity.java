package cat.ycatapp.xandone.ui.login;

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.ui.regist.RegistActivity;

/**
 * author: xandone
 * created on: 2018/3/6 15:09
 */

public class LoginActivity extends RxBaseActivity {
    @Override
    public void initInject() {

    }

    @Override
    public int setLayout() {
        return R.layout.act_login_layout;
    }

    @OnClick({R.id.act_login_regist})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.act_login_regist:
                startActivity(new Intent(LoginActivity.this, RegistActivity.class));
                break;
        }
    }
}
