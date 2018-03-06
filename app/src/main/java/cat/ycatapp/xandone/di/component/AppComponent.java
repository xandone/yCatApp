package cat.ycatapp.xandone.di.component;

import javax.inject.Singleton;

import cat.ycatapp.xandone.api.http.RetrofitHelper;
import cat.ycatapp.xandone.di.module.AppModule;
import cat.ycatapp.xandone.di.module.HttpModule;
import cat.ycatapp.xandone.model.DataManager;
import dagger.Component;

/**
 * author: xandone
 * created on: 2018/3/6 9:44
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    DataManager getDataManager();

    RetrofitHelper retrofitHelper();
}
