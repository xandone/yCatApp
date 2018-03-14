package cat.ycatapp.xandone.ui.bar;

import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;

/**
 * author: xandone
 * created on: 2018/3/6 13:35
 */

public class BarFragment extends RxBaseFragment {
    @BindView(R.id.toolBar)
    Toolbar toolbar;

    @Override
    public int setLayout() {
        return R.layout.frag_bar_layout;
    }

    @Override
    protected void initInject() {
//        getFragmentComponent().inject(this);
    }


    @Override
    public void initData() {
        super.initData();
        setToolBar(toolbar, getString(R.string.x_bar_title));
    }
}
