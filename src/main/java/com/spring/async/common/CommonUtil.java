package com.spring.async.common;


import java.time.Duration;

public class CommonUtil {

    public static void sleepService(Duration duration)
    {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
