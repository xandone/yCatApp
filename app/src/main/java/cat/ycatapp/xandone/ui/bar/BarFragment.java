package cat.ycatapp.xandone.ui.bar;

import cat.ycatapp.xandone.MainPresenter;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.RxBaseFragment;

/**
 * author: xandone
 * created on: 2018/3/6 13:35
 */

public class BarFragment extends RxBaseFragment<MainPresenter>{
    @Override
    public int setLayout() {
        return R.layout.frag_bar_layout;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }
}
