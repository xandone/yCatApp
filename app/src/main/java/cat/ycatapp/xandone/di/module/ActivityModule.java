package cat.ycatapp.xandone.di.module;

import android.app.Activity;

import cat.ycatapp.xandone.di.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * author: xandone
 * created on: 2018/3/6 8:42
 */

@Module
public class ActivityModule {
    private Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity() {
        return activity;
    }
}
