package com.norriswu.codetestbundlecalculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc =  new Scanner(System.in);
        System.out.println("G'Day mate! What's your name?\n");
        String name = sc.nextLine();
        System.out.println(greeting(name));
    }

    public static String greeting(String name) {
        return String.format("Hello %s!", name);
    }
}