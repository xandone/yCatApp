package cat.ycatapp.xandone.cache;

/**
 * author: xandone
 * Created on: 2018/4/12 17:32
 */

public class CacheBean {
    private String data;
    private String key;
    private long timeStamp;

    public CacheBean(String data, String key, long timeStamp) {
        this.data = data;
        this.key = key;
        this.timeStamp = timeStamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
