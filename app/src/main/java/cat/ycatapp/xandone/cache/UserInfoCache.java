package cat.ycatapp.xandone.cache;

import cat.ycatapp.xandone.model.bean.UserBean;

/**
 * author: xandone
 * created on: 2018/3/7 10:22
 */

public class UserInfoCache {

    private static boolean mLogin;
    private static UserBean mUserBean;

    public static boolean isLogin() {
        return mLogin;
    }

    public static void setLogin(boolean login) {
        UserInfoCache.mLogin = login;
    }

    public static UserBean getUserBean() {
        return mUserBean;
    }

    public static void setUserBean(UserBean userBean) {
        UserInfoCache.mUserBean = userBean;
    }
}
