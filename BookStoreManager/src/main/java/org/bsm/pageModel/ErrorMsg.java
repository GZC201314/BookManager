package org.bsm.pageModel;

public class ErrorMsg implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6447438075928976064L;


    private Integer errorCode;
    // 402 token 不一致 ,403
    private String errorMsg;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "ErrorMsg [errorCode=" + errorCode + ", errorMsg=" + errorMsg + "]";
    }


}
