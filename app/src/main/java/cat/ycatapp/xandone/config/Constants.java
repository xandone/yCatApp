package cat.ycatapp.xandone.config;

import android.os.Environment;

import java.io.File;

import cat.ycatapp.xandone.App;

/**
 * author: xandone
 * created on: 2017/11/27 17:41
 */

public class Constants {

    //================= TYPE ====================

    public static final int TYPE_ZHIHU = 101;

    //================= PATH ====================

    public static final String PATH_DATA = App.sContext.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "xandone" + File.separator + "ycat";

//------------------------KEY------------------------------

    public static final String BUGLY_ID = "";


    //================= PREFERENCE ====================

    public static final String SP_NIGHT_MODE = "night_mode";

    public static final String SP_NO_IMAGE = "no_image";

    public static final String SP_AUTO_CACHE = "auto_cache";

    public static final String SP_CURRENT_ITEM = "current_item";

    public static final String SP_LIKE_POINT = "like_point";

    public static final String SP_VERSION_POINT = "version_point";

    public static final String SP_MANAGER_POINT = "manager_point";

}
