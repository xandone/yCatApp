package cat.ycatapp.xandone.di.component;

import cat.ycatapp.xandone.MainActivity;
import cat.ycatapp.xandone.di.module.ActivityModule;
import cat.ycatapp.xandone.di.scope.ActivityScope;
import dagger.Component;

/**
 * author: xandone
 * created on: 2018/3/6 9:46
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
