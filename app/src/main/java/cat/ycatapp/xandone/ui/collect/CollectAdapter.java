package cat.ycatapp.xandone.ui.collect;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.uitils.TimeUtil;
import cat.ycatapp.xandone.uitils.imgload.XGlide;

/**
 * author: xandone
 * created on: 2018/7/26 17:58
 */
public class CollectAdapter extends BaseQuickAdapter<JokeBean, BaseViewHolder> {
    private Context context;

    public CollectAdapter(Context context, int layoutResId, @Nullable List<JokeBean> data) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, JokeBean item) {
        if (item == null) {
            return;
        }
        helper.setText(R.id.item_joke_user_name, item.getJoke_user_nick());
        helper.setText(R.id.item_joke_list_title, item.getTitle());
        helper.setText(R.id.item_joke_list_content, item.getContent());
        helper.setText(R.id.item_joke_list_collect_date, TimeUtil.getStringByFormat(item.getPost_time(), TimeUtil.dateFormat));

        ImageView imageView = helper.getView(R.id.item_joke_user_icon);
        XGlide.loadImage(Glide.with(context), imageView, item.getJoke_user_icon(), R.drawable.df_icon);
    }
}
