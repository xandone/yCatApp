package cat.ycatapp.xandone.ui.joke;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import butterknife.BindView;
import butterknife.OnClick;
import cat.ycatapp.xandone.R;
import cat.ycatapp.xandone.base.BaseActivity;
import cat.ycatapp.xandone.base.RxBaseActivity;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.JokeBean;
import cat.ycatapp.xandone.uitils.TimeUtil;
import cat.ycatapp.xandone.uitils.ToastUtils;
import cat.ycatapp.xandone.uitils.imgload.GlideLoader;
import cat.ycatapp.xandone.uitils.imgload.ImageLoadInterface;
import cat.ycatapp.xandone.uitils.imgload.XGlide;
import cat.ycatapp.xandone.widget.UserCircleIcon;

/**
 * author: xandone
 * created on: 2018/3/13 15:24
 */

public class JokeDetailsActivity extends RxBaseActivity<JokeDetailsPresenter> implements JokeDetailsContact.View {
    @BindView(R.id.act_joke_details_title)
    TextView act_joke_details_title;
    @BindView(R.id.act_joke_details_content)
    TextView act_joke_details_content;
    @BindView(R.id.act_joke_details_date)
    TextView act_joke_details_date;
    @BindView(R.id.act_joke_details_user_icon)
    UserCircleIcon act_joke_details_user_icon;
    @BindView(R.id.act_joke_details_user_name)
    TextView act_joke_details_user_name;
    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.act_joke_details_root)
    ConstraintLayout act_joke_details_root;
    @BindView(R.id.act_joke_details_bottom)
    RelativeLayout act_joke_details_bottom;
    @BindView(R.id.act_joke_details_like)
    TextView act_joke_details_like;
    @BindView(R.id.act_joke_details_comment_count)
    TextView act_joke_details_comment_count;

    private JokeBean.RowsBean jokeBean;
    private RequestManager requestManager;
    private boolean isShowBottom = true;

    private Drawable drawable1, drawable2;

    @Override
    public int setLayout() {
        return R.layout.act_joke_details_layout;
    }


    @Override
    public void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void initData() {
        super.initData();

        setToolBar(toolBar, getTitle().toString());
        requestManager = Glide.with(this);
        jokeBean = (JokeBean.RowsBean) getIntent().getSerializableExtra(JokeListAdapter.JOKEBEAN_TAG);
        if (jokeBean == null) {
            return;
        }
        drawable1 = getResources().getDrawable(R.drawable.zan_press);
        drawable2 = getResources().getDrawable(R.drawable.zan);
        drawable1.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getMinimumHeight());
        drawable2.setBounds(0, 0, drawable1.getIntrinsicWidth(), drawable1.getMinimumHeight());

        mPresenter.getThumbsJoke(jokeBean.getJoke_id(), jokeBean.getJoke_user_id());
        XGlide.loadImage(requestManager, act_joke_details_user_icon, jokeBean.getJoke_user_icon(), R.drawable.df_icon);

        act_joke_details_user_name.setText(jokeBean.getJoke_user_nick());
        act_joke_details_title.setText(jokeBean.getTitle());
        act_joke_details_content.setText(jokeBean.getContent());
        act_joke_details_date.setText(TimeUtil.getStringByFormat(jokeBean.getPost_time(), TimeUtil.dateFormat));
        act_joke_details_like.setText(String.valueOf(jokeBean.getArticle_like_count()));
        act_joke_details_comment_count.setText(String.valueOf(jokeBean.getArticle_comment_count()));

    }

    @OnClick({R.id.act_joke_details_root, R.id.act_joke_details_like})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.act_joke_details_root:
                isShowBottom = !isShowBottom;
                bottomAnim(isShowBottom);
                break;
            case R.id.act_joke_details_like:
                mPresenter.thumbsJoke(jokeBean.getJoke_id(), jokeBean.getJoke_user_id());
                break;
        }
    }

    public void bottomAnim(boolean isShow) {
        if (isShow) {
            animIn();
        } else {
            animOut();
        }
    }

    public void animIn() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(act_joke_details_bottom,
                "translationY",
                act_joke_details_bottom.getHeight(),
                0);
        objectAnimator.setDuration(300)
                .setRepeatCount(0);
        objectAnimator.start();
    }


    public void animOut() {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(act_joke_details_bottom,
                "translationY",
                0,
                act_joke_details_bottom.getHeight());
        objectAnimator.setDuration(300)
                .setRepeatCount(0);
        objectAnimator.start();
    }

    @Override
    public void showContent(BaseResponse baseResponse) {
        if (baseResponse == null) {
            return;
        }
        //已点赞
        if ("2".equals(baseResponse.getCode())) {
            act_joke_details_like.setCompoundDrawables(null, drawable1, null, null);
        } else {
            act_joke_details_like.setCompoundDrawables(null, drawable2, null, null);
        }
    }

    /**
     * 手动点赞结果
     *
     * @param baseResponse
     */
    @Override
    public void thumbsJokeResult(BaseResponse baseResponse) {
        if (baseResponse == null) {
            return;
        }
        //已点赞
        if ("2".equals(baseResponse.getCode())) {
            ToastUtils.showShort("老铁你已经点赞了");
        } else {
            act_joke_details_like.setCompoundDrawables(null, drawable1, null, null);
        }
    }


    @Override
    public void showMsg(String msg, int loadStatus) {
        ToastUtils.showShort(msg);
    }
}
