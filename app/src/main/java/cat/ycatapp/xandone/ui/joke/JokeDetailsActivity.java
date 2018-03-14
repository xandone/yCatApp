package cat.ycatapp.xandone.ui.joke;

import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.BaseActivity;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.uitils.TimeUtil;

/**
 * author: xandone
 * created on: 2018/3/13 15:24
 */

public class JokeDetailsActivity extends BaseActivity {
    @BindView(R.id.act_joke_details_title)
    TextView act_joke_details_title;
    @BindView(R.id.act_joke_details_content)
    TextView act_joke_details_content;
    @BindView(R.id.act_joke_details_date)
    TextView act_joke_details_date;
    @BindView(R.id.toolBar)
    Toolbar toolBar;

    private JokeBean.RowsBean jokeBean;

    @Override
    public int setLayout() {
        return R.layout.act_joke_details_layout;
    }


    @Override
    public void initData() {
        setToolBar(toolBar, getTitle().toString());
        jokeBean = (JokeBean.RowsBean) getIntent().getSerializableExtra(JokeListAdapter.JOKEBEAN_TAG);
        if (jokeBean == null) {
            return;
        }
        act_joke_details_title.setText(jokeBean.getTitle());
        act_joke_details_content.setText(jokeBean.getContent());
        act_joke_details_date.setText(TimeUtil.getStringByFormat(jokeBean.getPost_time(), TimeUtil.dateFormat));
    }
}
