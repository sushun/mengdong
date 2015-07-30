package cn.md.trainclient.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import cn.md.trainclient.utils.Logger;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "train_client";
    private static final int DATABASE_VERSION = 12;

    private Map<String, Dao> daos = new HashMap<>();

    private static DataBaseHelper instance;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
//        try {
//            TableUtils.createTable(connectionSource, ContactsLabel.class);
//        } catch (SQLException e) {
//            Logger.e(DataBaseHelper.class.getSimpleName(), "创建数据库失败", e);
//            e.printStackTrace();
//        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
//        try {
//            // 更新时，不销毁原有数据
//            TableUtils.dropTable(connectionSource, ContactsLabel.class, true);
//            onCreate(sqLiteDatabase, connectionSource);
//        } catch (SQLException e) {
//            Logger.e(DataBaseHelper.class.getSimpleName(), "更新数据库失败", e);
//            e.printStackTrace();
//        }
    }

    /**
     * 单例获取该Helper
     */
    public static synchronized DataBaseHelper getHelper(Context context) {
        context = context.getApplicationContext();
        if (instance == null) {
            synchronized (DataBaseHelper.class) {
                if (instance == null)
                    instance = OpenHelperManager.getHelper(context, DataBaseHelper.class);
            }
        }
        return instance;
    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        super.close();
    }

}
