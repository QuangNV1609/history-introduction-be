package com.uet.quangnv.exception.domain;

public class ResoureNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ResoureNotFoundException() {
        super();
    }

    public ResoureNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResoureNotFoundException(String message) {
        super(message);
    }

    public ResoureNotFoundException(Throwable cause) {
        super(cause);
    }

}
