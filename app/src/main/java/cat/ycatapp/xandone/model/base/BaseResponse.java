package cat.ycatapp.xandone.model.base;

/**
 * author: xandone
 * created on: 2018/3/6 8:57
 */

public class BaseResponse<T> {

    /**
     * code : 1
     * msg : 注册成功
     * dataList : null
     */

    private String code;
    private String msg;
    private T dataList;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getDataList() {
        return dataList;
    }

    public void setDataList(T dataList) {
        this.dataList = dataList;
    }
}
