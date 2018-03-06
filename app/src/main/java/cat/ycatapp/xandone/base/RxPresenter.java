package cat.ycatapp.xandone.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * author: xandone
 * created on: 2018/3/5 15:43
 */

public class RxPresenter<T extends BaseView> implements BasePresenter<T> {
    protected T view;
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
        unSubscrible();
    }

    public void unSubscrible() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    public void addSubscrible(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }
}
