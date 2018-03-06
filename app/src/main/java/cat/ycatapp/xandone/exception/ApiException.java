package cat.ycatapp.xandone.exception;

/**
 * author: xandone
 * created on: 2017/11/29 14:08
 */

public class ApiException extends Exception {
    private int code;

    public ApiException(String msg) {
        super(msg);
    }

    public ApiException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
