package cat.ycatapp.xandone.config;

import android.os.Environment;

import java.io.File;

import cat.ycatapp.xandone.App;

/**
 * author: xandone
 * created on: 2017/11/27 17:41
 */

public class Constants {

    //================= PATH ====================

    public static final String PATH_DATA = App.sContext.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "xandone" + File.separator + "ycat";

//------------------------SP_KEY------------------------------

    public static final String USER_INFO_KEY = "Constants_USER_INFO_KEY";

//------------------------SP_NAME------------------------------

    public static final String USER_INFO_NAME = "Constants_USER_INFO_NAME";


}
