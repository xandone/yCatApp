package cat.ycatapp.xandone.config;

import android.os.Environment;

import java.io.File;

import cat.ycatapp.xandone.App;

/**
 * author: xandone
 * created on: 2017/11/27 17:41
 */

public class Constants {
    /**
     * App独立文件,不随app一起卸载
     * Environment.getExternalStorageDirectory()：sdCard的路径为mnt/sdcard/ 即为SD卡根路径
     * <p>
     * APP私有文件，随app一起卸载
     * getFilesDir()： data/data/< package name >/files/
     * <p>
     * App专属文件，随app一起卸载
     * getExternalFilesDir/getExternalCacheDir：mnt/sdcard/Android/data/< package name >/files/…
     */


    //================= PATH ====================

    public static final String PATH_DATA = App.sContext.getCacheDir().getAbsolutePath() + File.separator + "data";

    public static final String PATH_CACHE = PATH_DATA + "/ycatCache";

    public static final String PATH_CACHE_EXTERNAL = App.sContext.getExternalCacheDir() + File.separator + "ycat" + File.separator;

    public static final String PATH_SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "xandone" + File.separator + "ycat";

//------------------------SP_KEY------------------------------

    public static final String USER_INFO_KEY = "Constants_USER_INFO_KEY";

//------------------------SP_NAME------------------------------

    public static final String USER_INFO_NAME = "Constants_USER_INFO_NAME";


}
