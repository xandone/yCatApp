package cat.ycatapp.xandone.base;

import javax.inject.Inject;

import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.di.component.DaggerFragmentComponent;
import cat.ycatapp.xandone.di.component.FragmentComponent;
import cat.ycatapp.xandone.di.module.FragmentModule;

/**
 * author: xandone
 * created on: 2018/3/5 15:31
 */

public abstract class RxBaseFragment<T extends RxPresenter> extends BaseFragment {

    @Inject
    protected T mPresenter;

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .appComponent(App.getAppComponent())
                .fragmentModule(getFragmentModule())
                .build();
    }


    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void initData() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract void initInject();

    @Override
    public void onDestroyView() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }


    /**
     * 懒加载重写
     */
    @Override
    protected void lazyLoadData() {

    }
}
