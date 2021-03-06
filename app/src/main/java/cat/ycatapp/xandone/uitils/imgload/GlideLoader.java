package cat.ycatapp.xandone.uitils.imgload;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import cat.ycatapp.xandone.R;

/**
 * author: xandone
 * created on: 2018/3/7 10:44
 */

public class GlideLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context.getApplicationContext())
                .load(path)
                .placeholder(R.drawable.df_icon)
                .into(imageView);
    }
}
