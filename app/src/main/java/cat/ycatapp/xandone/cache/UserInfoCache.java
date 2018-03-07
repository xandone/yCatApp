package cat.ycatapp.xandone.cache;

/**
 * author: xandone
 * created on: 2018/3/7 10:22
 */

public class UserInfoCache {

    private static boolean mLogin;

    public static boolean isLogin() {
        return mLogin;
    }

    public static void setLogin(boolean login) {
        UserInfoCache.mLogin = login;
    }
}
