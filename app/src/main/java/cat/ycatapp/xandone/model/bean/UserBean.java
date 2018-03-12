package cat.ycatapp.xandone.model.bean;

import java.io.Serializable;

/**
 * author: xandone
 * created on: 2018/3/7 10:23
 */

public class UserBean implements Serializable {
    private static final long serialVersionUID = -4222787434690549348L;

    private String userName;
    private String userPsw;
    private String userId;
    private String nickName;
    private String iconUrl;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
}
