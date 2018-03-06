package cat.ycatapp.xandone.di.component;

import android.app.Activity;


import cat.ycatapp.xandone.di.module.FragmentModule;
import cat.ycatapp.xandone.di.scope.FragmentScope;
import cat.ycatapp.xandone.ui.bar.BarFragment;
import cat.ycatapp.xandone.ui.info.InfoFragment;
import cat.ycatapp.xandone.ui.joke.JokeFragment;
import dagger.Component;

/**
 * author: xandone
 * created on: 2018/3/6 9:42
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

//    void inject(JokeFragment jokeFragment);
//
//    void inject(BarFragment jokeFragment);
//
//    void inject(InfoFragment jokeFragment);

}
