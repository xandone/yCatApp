package cat.ycatapp.xandone.ui.jokeadd;

import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import butterknife.BindView;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseActivity;

/**
 * Created by xandone on 2018/3/30.
 */

public class JokeAddActivity extends RxBaseActivity {
    @BindView(R.id.act_jokeadd_title)
    EditText act_jokeadd_title;
    @BindView(R.id.act_jokeadd_content)
    EditText act_jokeadd_content;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    @Override
    public void initInject() {

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
}
