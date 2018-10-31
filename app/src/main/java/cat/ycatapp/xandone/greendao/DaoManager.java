package cat.ycatapp.xandone.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.QueryBuilder;

import cat.ycatapp.xandone.greendao.gen.DaoMaster;
import cat.ycatapp.xandone.greendao.gen.DaoSession;

/**
 * author: xandone
 * created on: 2018/7/26 17:31
 */
public class DaoManager {
    private static final String TAG = DaoManager.class.getSimpleName();
    private static final String DB_NAME = "data.db";

    private Context context;

    private volatile static DaoManager manager = new DaoManager();
    private static DaoMaster sDaoMaster;
    private static OpenHelper sHelper;
    private static DaoSession sDaoSession;

    public static DaoManager getInstance() {
        return manager;
    }

    public void init(Context context) {
        this.context = context;
    }

    /**
     * 判断是否有存在数据库，如果没有则创建
     *
     * @return
     */
    public DaoMaster getDaoMaster() {
        if (sDaoMaster == null) {
            OpenHelper helper = new OpenHelper(context, DB_NAME);
            sDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return sDaoMaster;
    }

    /**
     * 完成对数据库的添加、删除、修改、查询操作，仅仅是一个接口
     *
     * @return
     */
    public DaoSession getDaoSession() {
        if (sDaoSession == null) {
            if (sDaoMaster == null) {
                sDaoMaster = getDaoMaster();
            }
            sDaoSession = sDaoMaster.newSession();
        }
        return sDaoSession;
    }

    /**
     * 打开输出日志，默认关闭
     */
    public void setDebug() {
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        closeHelper();
        closeDaoSession();
    }

    public void closeHelper() {
        if (sHelper != null) {
            sHelper.close();
            sHelper = null;
        }
    }

    public void closeDaoSession() {
        if (sDaoSession != null) {
            sDaoSession.clear();
            sDaoSession = null;
        }
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
