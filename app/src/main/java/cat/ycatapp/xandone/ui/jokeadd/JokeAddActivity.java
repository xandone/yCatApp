package cat.ycatapp.xandone.ui.jokeadd;

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

    @Override
    public void initInject() {

    }

    @Override
    public int setLayout() {
        return R.layout.act_jokeadd_layout;
    }
}
