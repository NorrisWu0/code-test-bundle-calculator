package com.norriswu.codetestbundlecalculator.utils;


import com.google.gson.Gson;

import java.io.FileWriter;

public class JSON {
    private static final Gson gson = new Gson();

    public static String stringify(Object obj) {
        return gson.toJson(obj);
    }

    public static void stringify(Object obj, FileWriter fw) {
        gson.toJson(obj, fw);
    }

    public static Gson getGsonInstance() {
        return gson;
    }
}
