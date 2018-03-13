package cat.ycatapp.xandone.model.bean;

import java.util.List;

/**
 * author: xandone
 * created on: 2018/3/12 23:05
 */

public class JokeBean {

    /**
     * total : 3
     * rows : [{"joke_id":"1000","joke_user_id":"10000","title":"笑话0","content":"从前山上有个庙0","post_time":1520506026000,"article_like_count":0,"article_comment_count":0},{"joke_id":"1001","joke_user_id":"10001","title":"笑话1","content":"从前山上有个庙1","post_time":1520506027000,"article_like_count":0,"article_comment_count":0},{"joke_id":"1002","joke_user_id":"10002","title":"笑话2","content":"从前山上有个庙2","post_time":1520506027000,"article_like_count":0,"article_comment_count":0}]
     */

    private int total;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * joke_id : 1000
         * joke_user_id : 10000
         * title : 笑话0
         * content : 从前山上有个庙0
         * post_time : 1520506026000
         * article_like_count : 0
         * article_comment_count : 0
         */

        private String joke_id;
        private String joke_user_id;
        private String title;
        private String content;
        private long post_time;
        private int article_like_count;
        private int article_comment_count;

        public String getJoke_id() {
            return joke_id;
        }

        public void setJoke_id(String joke_id) {
            this.joke_id = joke_id;
        }

        public String getJoke_user_id() {
            return joke_user_id;
        }

        public void setJoke_user_id(String joke_user_id) {
            this.joke_user_id = joke_user_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public long getPost_time() {
            return post_time;
        }

        public void setPost_time(long post_time) {
            this.post_time = post_time;
        }

        public int getArticle_like_count() {
            return article_like_count;
        }

        public void setArticle_like_count(int article_like_count) {
            this.article_like_count = article_like_count;
        }

        public int getArticle_comment_count() {
            return article_comment_count;
        }

        public void setArticle_comment_count(int article_comment_count) {
            this.article_comment_count = article_comment_count;
        }
    }
}
