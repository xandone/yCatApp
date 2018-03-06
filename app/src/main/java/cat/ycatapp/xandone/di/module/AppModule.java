package cat.ycatapp.xandone.di.module;

import javax.inject.Singleton;

import cat.ycatapp.xandone.App;
import cat.ycatapp.xandone.api.http.HttpHelper;
import cat.ycatapp.xandone.api.http.RetrofitHelper;
import cat.ycatapp.xandone.model.DataManager;
import dagger.Module;
import dagger.Provides;

/**
 * author: xandone
 * created on: 2018/3/6 8:46
 */

@Module
public class AppModule {
    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper retrofitHelper) {
        return retrofitHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper) {
        return new DataManager(httpHelper);
    }
}
