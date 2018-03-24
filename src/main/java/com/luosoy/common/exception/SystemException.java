
package com.luosoy.common.exception;


public class SystemException extends BaseException {


    public static final String REQUEST_EXCEPTION = "000101";

    public static final String RESPONSE_EXCEPTION = "000102";

    public static final String JSON_EXCEPTION = "000203";

    public static final String REQUEST_PARAM_EXCEPTION = "000204";


    public SystemException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }


    public SystemException(String message, String errorCode) {
        super(message, new RuntimeException(message), errorCode);
    }

}
