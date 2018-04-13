package cat.ycatapp.xandone.cache;

import android.os.Environment;
import android.text.format.Formatter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.config.Constants;
import cat.ycatapp.xandone.uitils.MD5Utils;
import cat.ycatapp.xandone.uitils.ToastUtils;

/**
 * author: xandone
 * Created on: 2018/4/12 17:25
 */

public class CacheUtils {
    public static final int SDCARD_MIN_SPACE = 50;
    public static final int MAX_CACHE = 500;

    /**
     * 根据key读取缓存
     *
     * @param key
     * @return
     */
    public static String getFileCache(String key) {
        String md5Key = MD5Utils.decode16(key);
        if (!isCacheFile(md5Key)) {
            return null;
        }
        CacheBean cacheBean = getCacheFromKey(md5Key);

        if (cacheBean == null) {
            return null;
        }
        return cacheBean.getData();

    }

    /**
     * 读取缓存bean
     *
     * @param key
     * @return
     */
    public static CacheBean getCacheFromKey(String key) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Object object = null;
        CacheBean cacheBean = null;
        File file = new File(Constants.PATH_CACHE_EXTERNAL + key);
        try {
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            object = ois.readObject();

            if (object != null) {
                cacheBean = (CacheBean) object;
            }

            if (cacheBean == null) {
                return null;
            }

            if (cacheBean.getTimeStamp() < System.currentTimeMillis()) {
                file.delete();
                return null;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

        return cacheBean;
    }

    /**
     * 根据key检查是否有该缓存文件
     *
     * @param key
     * @return
     */
    public static boolean isCacheFile(String key) {
        File file = new File(Constants.PATH_CACHE_EXTERNAL + key);
        return file.exists();
    }

    public static void putFileCache(CacheBean cacheBean) {
        if (getSdCardSpace() < SDCARD_MIN_SPACE) {
            ToastUtils.showShort("内存空间不足");
            return;
        }

    }

    public static long getSdCardSpace() {
        File sdcard_filedir = Environment.getExternalStorageDirectory();//得到sdcard的目录作为一个文件对象
        long usableSpace = sdcard_filedir.getUsableSpace();//获取文件目录对象剩余空间
        long totalSpace = sdcard_filedir.getTotalSpace();
        String usableSpace_str = Formatter.formatFileSize(App.sContext, usableSpace);
        String totalSpace_str = Formatter.formatFileSize(App.sContext, totalSpace);

        return usableSpace / (1024 * 1024);
    }
}
