package cat.ycatapp.xandone.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

import cat.ycatapp.xandone.greendao.gen.DaoMaster;
import cat.ycatapp.xandone.greendao.gen.DaoSession;

/**
 * author: xandone
 * created on: 2018/7/26 17:31
 */
public class DaoManager {
    private static final String DB_NAME = "data.db";
    private static DaoMaster.DevOpenHelper mOpenHelper;
    private static DaoSession mSession;
    private static DaoManager mInstance;

    public static DaoManager getInstance(Context context) {
        if (null == mInstance) {
            synchronized (DaoManager.class) {
                if (null == mInstance) {
                    mInstance = new DaoManager(context);

                }
            }
        }
        return mInstance;
    }

    private DaoManager(Context context) {
        init(context);
    }

    private void init(Context context) {
        mOpenHelper = new OpenHelper(context.getApplicationContext(), DB_NAME);
        DaoMaster daoMaster = new DaoMaster(mOpenHelper.getWritableDatabase());
        mSession = daoMaster.newSession();
    }

    public DaoSession getSession() {
        return mSession;
    }

    public static DaoSession getSession(Context context) {
        return getInstance(context).getSession();
    }


    //自定义数据库升级工具, greendao生成的工具默认会删除所有表
    private class OpenHelper extends DaoMaster.DevOpenHelper {

        public OpenHelper(Context context, String name) {
            super(context, name);
        }

        public OpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
            super(context, name, factory);
        }

        @Override
        public void onUpgrade(Database db, int oldVersion, int newVersion) {
            //数据库升级
            switch (newVersion) {
                case 1:
                case 2:
                default:
                    break;
            }
        }
    }
}
