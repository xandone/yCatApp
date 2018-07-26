package cat.ycatapp.xandone.greendao.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import cat.ycatapp.xandone.model.bean.JokeBean;

import cat.ycatapp.xandone.greendao.gen.JokeBeanDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig jokeBeanDaoConfig;

    private final JokeBeanDao jokeBeanDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        jokeBeanDaoConfig = daoConfigMap.get(JokeBeanDao.class).clone();
        jokeBeanDaoConfig.initIdentityScope(type);

        jokeBeanDao = new JokeBeanDao(jokeBeanDaoConfig, this);

        registerDao(JokeBean.class, jokeBeanDao);
    }
    
    public void clear() {
        jokeBeanDaoConfig.clearIdentityScope();
    }

    public JokeBeanDao getJokeBeanDao() {
        return jokeBeanDao;
    }

}
