package cat.ycatapp.xandone.ui.login;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.App;
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
import cat.ycatapp.xandone.uitils.AnimUtils;
import cat.ycatapp.xandone.uitils.GsonUtil;
import cat.ycatapp.xandone.uitils.SPUtils;
import cat.ycatapp.xandone.uitils.SimpleUtils;
import cat.ycatapp.xandone.uitils.ToastUtils;
import cat.ycatapp.xandone.uitils.XString;
import cat.ycatapp.xandone.widget.DrawableTextView;
import cat.ycatapp.xandone.widget.KeyboardWatcher;

/**
 * author: xandone
 * created on: 2018/3/6 15:09
 */

public class LoginActivity extends RxBaseActivity<LoginPresenter> implements LoginContact.View, KeyboardWatcher.SoftKeyboardStateListener {
    @BindView(R.id.act_login_et_email)
    EditText act_login_et_email;
    @BindView(R.id.act_login_et_psw)
    EditText act_login_et_psw;
    @BindView(R.id.body)
    ConstraintLayout body;
    @BindView(R.id.act_login_title)
    DrawableTextView act_login_title;

    private KeyboardWatcher keyboardWatcher;

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

        keyboardWatcher = new KeyboardWatcher(findViewById(Window.ID_ANDROID_CONTENT));
        keyboardWatcher.addSoftKeyboardStateListener(this);
    }

    @OnClick({R.id.act_login_regist, R.id.act_login_btn, R.id.close})
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
            case R.id.close:
                finish();
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
        keyboardWatcher.removeSoftKeyboardStateListener(this);
    }


    @Override
    public void onSoftKeyboardOpened(int keyboardSize) {
        int[] location = new int[2];
        body.getLocationOnScreen(location); //获取body在屏幕中的坐标,控件左上角
        int x = location[0];
        int y = location[1];
        int bottom = App.SCREEN_HEIGHT - (y + body.getHeight());
        if (keyboardSize > bottom) {
            ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", 0.0f, -(keyboardSize - bottom));
            mAnimatorTranslateY.setDuration(300);
            mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
            mAnimatorTranslateY.start();
            AnimUtils.zoomIn(act_login_title, keyboardSize - bottom, 0.8f);

        }
    }

    @Override
    public void onSoftKeyboardClosed() {
        ObjectAnimator mAnimatorTranslateY = ObjectAnimator.ofFloat(body, "translationY", body.getTranslationY(), 0);
        mAnimatorTranslateY.setDuration(300);
        mAnimatorTranslateY.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimatorTranslateY.start();
        AnimUtils.zoomOut(act_login_title, 0.8f);
    }
}
