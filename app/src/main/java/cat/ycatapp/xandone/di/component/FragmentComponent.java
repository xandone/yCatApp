package cat.ycatapp.xandone.di.component;

import android.app.Activity;


import cat.ycatapp.xandone.di.module.FragmentModule;
import cat.ycatapp.xandone.di.scope.FragmentScope;
import cat.ycatapp.xandone.ui.bar.ImageFragment;
import cat.ycatapp.xandone.ui.info.InfoFragment;
import cat.ycatapp.xandone.ui.joke.JokeFragment;
import cat.ycatapp.xandone.ui.video.VideoListFragment;
import cat.ycatapp.xandone.ui.videodetails.VideoOtherFragment;
import dagger.Component;

/**
 * author: xandone
 * created on: 2018/3/6 9:42
 */

@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(JokeFragment jokeFragment);

    //
    void inject(ImageFragment imageFragment);

    //
    void inject(InfoFragment jokeFragment);

    void inject(VideoListFragment videoListFragment);

    void inject(VideoOtherFragment videoOtherFragment);

}
