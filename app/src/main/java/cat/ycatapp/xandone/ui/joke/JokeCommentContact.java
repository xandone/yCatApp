package cat.ycatapp.xandone.ui.joke;

import java.util.List;

import cat.ycatapp.xandone.base.BasePresenter;
import cat.ycatapp.xandone.base.BaseView;
import cat.ycatapp.xandone.model.base.BaseResponse;
import cat.ycatapp.xandone.model.bean.CommentBean;
import cat.ycatapp.xandone.model.bean.JokeBean;

/**
 * author: xandone
 * created on: 2018/3/16 9:08
 */

public interface JokeCommentContact {
    int MODE_ONE = 0;
    int MODE_MORE = 1;

    interface MyView extends BaseView {
        void showContent(CommentBean commentBean);

        void showContentMore(CommentBean commentBean);

        void showCommentResult(BaseResponse<List<CommentBean.RowsBean>> response);

        void showCommentError();
    }

    interface Presenter extends BasePresenter<MyView> {
        void getContentList(int page, int rows, String jokeId, int mode);

        void addComment(String jokeId, String userId, String details);
    }

}
