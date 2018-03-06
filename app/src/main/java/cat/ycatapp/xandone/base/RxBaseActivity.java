package cat.ycatapp.xandone.base;

import javax.inject.Inject;

import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.di.component.ActivityComponent;
import cat.ycatapp.xandone.di.component.DaggerActivityComponent;
import cat.ycatapp.xandone.di.module.ActivityModule;

/**
 * author: xandone
 * created on: 2018/3/5 15:30
 */

public abstract class RxBaseActivity<T extends RxPresenter> extends BaseActivity {

    @Inject
    protected T mPresenter;

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();

    }

    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public abstract void initInject();

    @Override
    public void initData() {
        initInject();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }


    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

}
