package cat.ycatapp.xandone.base;

/**
 * author: xandone
 * created on: 2018/3/5 15:40
 */

public interface BaseView {
    void showMsg(String msg);

    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();
}
