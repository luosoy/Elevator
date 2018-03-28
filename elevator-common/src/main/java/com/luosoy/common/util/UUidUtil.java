package com.luosoy.common.util;


import java.util.UUID;

public class UUidUtil {

    public static String generateThreadUUId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }
}
