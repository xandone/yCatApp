package cat.ycatapp.xandone;

import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import cat.ycatapp.xandone.base.RxBaseActivity;

public class MainActivity extends RxBaseActivity<MainPresenter> {
    @BindView(R.id.btn)
    Button button;

    @Override
    public int setLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        super.initData();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.regist("1234@qq.com", "psw123", "大牛");
            }
        });
    }
}
