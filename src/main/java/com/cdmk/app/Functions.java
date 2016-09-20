package com.cdmk.app;

/**
 * Created by javon on 20/09/2016.
 */
public final class Functions {
    private Functions() {}

    public static String format(String data) {
        return (data != null) ? data.replace("\n", "<br/>") : null;
    }
}