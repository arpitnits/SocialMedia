package org.example.Utils;

public abstract class CommonUtils {

    public static String getUUID() {
        return java.util.UUID.randomUUID().toString();
    }
}
