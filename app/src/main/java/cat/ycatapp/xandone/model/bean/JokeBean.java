package cat.ycatapp.xandone.model.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

/**
 * author: xandone
 * created on: 2018/7/26 15:53
 */
@Entity
public class JokeBean implements Serializable {
    private static final long serialVersionUID = 5634444343520321563L;
    /**
     * joke_id : 100
     * joke_user_id : 1000
     * title : 兵车行
     * content : 车辚辚，马萧萧，行人弓箭各在腰。
     * post_time : 1520929412000
     * article_like_count : 0
     * article_comment_count : 0
     * joke_user_nick : null
     * joke_user_icon : null
     */
    @Id(autoincrement = true)
    private Long jokeDaoId;

    @Property
    private String joke_id;
    private String joke_user_id;
    private String title;
    private String content;
    private long post_time;
    private int article_like_count;
    private int article_comment_count;
    private String joke_user_nick;
    private String joke_user_icon;

    @Generated(hash = 2141584347)
    public JokeBean(Long jokeDaoId, String joke_id, String joke_user_id,
                    String title, String content, long post_time, int article_like_count,
                    int article_comment_count, String joke_user_nick,
                    String joke_user_icon) {
        this.jokeDaoId = jokeDaoId;
        this.joke_id = joke_id;
        this.joke_user_id = joke_user_id;
        this.title = title;
        this.content = content;
        this.post_time = post_time;
        this.article_like_count = article_like_count;
        this.article_comment_count = article_comment_count;
        this.joke_user_nick = joke_user_nick;
        this.joke_user_icon = joke_user_icon;
    }

    @Generated(hash = 46550206)
    public JokeBean() {
    }

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

    public String getJoke_user_nick() {
        return joke_user_nick;
    }

    public void setJoke_user_nick(String joke_user_nick) {
        this.joke_user_nick = joke_user_nick;
    }

    public String getJoke_user_icon() {
        return joke_user_icon;
    }

    public void setJoke_user_icon(String joke_user_icon) {
        this.joke_user_icon = joke_user_icon;
    }

    public Long getJokeDaoId() {
        return this.jokeDaoId;
    }

    public void setJokeDaoId(Long jokeDaoId) {
        this.jokeDaoId = jokeDaoId;
    }
}
