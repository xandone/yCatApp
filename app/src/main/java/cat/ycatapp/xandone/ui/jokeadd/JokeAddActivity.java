package cat.ycatapp.xandone.ui.jokeadd;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.cache.UserInfoCache;
import cat.ycatapp.xandone.uitils.SimpleUtils;
import cat.ycatapp.xandone.uitils.ToastUtils;
import cat.ycatapp.xandone.uitils.XString;

/**
 * author: xandone
 * Created by xandone on 2018/3/30.
 */

public class JokeAddActivity extends RxBaseActivity<JokeAddPresenter> implements JokeAddContact.MyView {
    @BindView(R.id.act_jokeadd_title)
    EditText act_jokeadd_title;
    @BindView(R.id.act_jokeadd_content)
    EditText act_jokeadd_content;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.toolbar_commit)
    TextView toolbar_commit;

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public int setLayout() {
        return R.layout.act_jokeadd_layout;
    }

    @Override
    public void initData() {
        super.initData();
        setToolBar(toolBar, getTitle().toString());
    }

    @OnClick({R.id.toolbar_commit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.toolbar_commit:
                if (!UserInfoCache.isLogin()) {
                    ToastUtils.showShort("请先登录");
                    return;
                }
                String title = act_jokeadd_title.getText().toString();
                String content = act_jokeadd_content.getText().toString();
                String userId = UserInfoCache.getUserBean().getUserId();

                if (XString.isEmpty(title)) {
                    ToastUtils.showShort("请输入标题");
                    break;
                }
                if (XString.isEmpty(content)) {
                    ToastUtils.showShort("请输入内容");
                    break;
                }

                SimpleUtils.hideSoftInput(this);
                showLoadingDialog(false);
                mPresenter.addJoke(title, userId, content);
                break;
        }
    }

    @Override
    public void showSuccess() {
        ToastUtils.showShort("请求成功");
        finish();
    }

    @Override
    public void showFail() {
        ToastUtils.showShort("请求失败,请稍后重试");
    }

    @Override
    public void showMsg(String msg, int loadStatus) {
        ToastUtils.showShort(msg);
    }
}
