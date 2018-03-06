package cat.ycatapp.xandone;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import cat.ycatapp.xandone.di.component.AppComponent;
import cat.ycatapp.xandone.di.component.DaggerAppComponent;
import cat.ycatapp.xandone.di.module.AppModule;

/**
 * author: xandone
 * created on: 2018/3/5 15:35
 */

public class App extends MultiDexApplication {

    public static AppComponent appComponent;
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule())
                    .build();
        }
        return appComponent;
    }
}
