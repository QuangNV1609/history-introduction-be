package com.uet.quangnv.exception.domain;

public class DataFormatWrong extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DataFormatWrong() {
    }

    public DataFormatWrong(String message) {
        super(message);
    }

    public DataFormatWrong(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFormatWrong(Throwable cause) {
        super(cause);
    }

    public DataFormatWrong(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
