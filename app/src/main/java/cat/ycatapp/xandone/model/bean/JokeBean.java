package cat.ycatapp.xandone.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * author: xandone
 * created on: 2018/3/12 23:05
 */

public class JokeBean {


    /**
     * total : 6
     * rows : [{"joke_id":"100","joke_user_id":"1000","title":"兵车行","content":"车辚辚，马萧萧，行人弓箭各在腰。\r\n\r\n爷娘妻子走相送，尘埃不见咸阳桥。\r\n\r\n牵衣顿足拦道哭，哭声直上干云霄。\r\n\r\n道傍过者问行人，行人但云点行频。\r\n\r\n或从十五北防河，便至四十西营田。\r\n\r\n去时里正与裹头，归来头白还戍边。\r\n\r\n边庭流血成海水，武皇开边意未已。\r\n\r\n君不闻汉家山东二百州，千村万落生荆杞。\r\n\r\n纵有健妇把锄犁，禾生陇亩无东西。\r\n\r\n况复秦兵耐苦战，被驱不异犬与鸡。\r\n\r\n长者虽有问，役夫敢申恨？\r\n\r\n且如今年冬，未休关西卒。\r\n\r\n县官急索租，租税从何出？\r\n\r\n信知生男恶，反是生女好。\r\n\r\n生女犹得嫁比邻，生男埋没随百草。\r\n\r\n君不见，青海头，古来白骨无人收。\r\n\r\n新鬼烦冤旧鬼哭，天阴雨湿声啾啾！","post_time":1520929412000,"article_like_count":0,"article_comment_count":0,"joke_user_nick":null,"joke_user_icon":null},{"joke_id":"100","joke_user_id":"1000","title":"将进酒","content":"君不见，黄河之水天上来，奔流到海不复回。\r\n\r\n君不见，高堂明镜悲白发，朝如青丝暮成雪。\r\n\r\n人生得意须尽欢，莫使金樽空对月。\r\n\r\n天生我材必有用，千金散尽还复来。\r\n\r\n烹羊宰牛且为乐，会须一饮三百杯。\r\n\r\n岑夫子，丹丘生，将进酒，杯莫停。\r\n\r\n与君歌一曲，请君为我倾耳听。\r\n\r\n钟鼓馔玉不足贵，但愿长醉不复醒。\r\n\r\n古来圣贤皆寂寞，惟有饮者留其名。\r\n\r\n陈王昔时宴平乐，斗酒十千恣欢谑。\r\n\r\n主人何为言少钱，径须沽取对君酌。\r\n\r\n五花马，千金裘，呼儿将出换美酒，与尔同销万古愁。","post_time":1520955159000,"article_like_count":0,"article_comment_count":0,"joke_user_nick":null,"joke_user_icon":null},{"joke_id":"100","joke_user_id":"1000","title":"春望","content":"国破山河在，城春草木深。\r\n感时花溅泪，恨别鸟惊心。\r\n烽火连三月，家书抵万金。\r\n白头搔更短，浑欲不胜簪。","post_time":1520955259000,"article_like_count":0,"article_comment_count":0,"joke_user_nick":null,"joke_user_icon":null},{"joke_id":"100","joke_user_id":"1000","title":"春夜喜雨","content":"好雨知时节，当春乃发生。\r\n随风潜入夜，润物细无声。\r\n野径云俱黑，江船火独明。\r\n晓看红湿处，花重锦官城。","post_time":1520955297000,"article_like_count":0,"article_comment_count":0,"joke_user_nick":null,"joke_user_icon":null},{"joke_id":"100","joke_user_id":"1000","title":"登岳阳楼","content":"昔闻洞庭水，今上岳阳楼。\r\n吴楚东南坼，乾坤日夜浮。\r\n亲朋无一字，老病有孤舟。\r\n戎马关山北，凭轩涕泗流。","post_time":1520955330000,"article_like_count":0,"article_comment_count":0,"joke_user_nick":null,"joke_user_icon":null},{"joke_id":"100","joke_user_id":"0001","title":"登岳阳楼","content":"昔闻洞庭水，今上岳阳楼。\r\n吴楚东南坼，乾坤日夜浮。\r\n亲朋无一字，老病有孤舟。\r\n戎马关山北，凭轩涕泗流。","post_time":1520997037000,"article_like_count":0,"article_comment_count":0,"joke_user_nick":"二虎0","joke_user_icon":null}]
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

    public static class RowsBean implements Serializable{
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

        private String joke_id;
        private String joke_user_id;
        private String title;
        private String content;
        private long post_time;
        private int article_like_count;
        private int article_comment_count;
        private String joke_user_nick;
        private String joke_user_icon;

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
    }
}
