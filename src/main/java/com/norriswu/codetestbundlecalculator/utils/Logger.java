package com.norriswu.codetestbundlecalculator.utils;

import com.google.common.flogger.FluentLogger;

public class Logger {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    public static void info(String message) {
        System.out.println(message);
//        logger.atInfo().log(message);
    }
}
