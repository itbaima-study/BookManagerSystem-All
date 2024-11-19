package com.test.util;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class InputUtil {

    private static final Scanner scanner = new Scanner(System.in);

    public static int nextInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                sendErrorAndWait("您输入的结果不是一个数字，请重新输入！");
            } finally {
                scanner.nextLine();  //在最后跳过\n或所有未正确读取的内容
            }
        }
    }

    public static <T> T nextEntity(String prompt, Function<Integer, T> getter) {
        while (true) {
            try {
                System.out.print(prompt);
                int i = scanner.nextInt();
                T entity = getter.apply(i);
                if(entity != null) {
                    System.out.println("已选择: " + entity);
                    return entity;
                } else {
                    sendErrorAndWait("未在数据库中查询到此ID数据，请重新输入！");
                }
            } catch (InputMismatchException e) {
                sendErrorAndWait("您输入的结果不是一个数字，请重新输入！");
            } finally {
                scanner.nextLine();
            }
        }
    }

    public static String nextLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public static String nextLine(String prompt, String... values) {
        List<String> list = Arrays.asList(values);
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            if(list.contains(input)) {
                return input;
            }
            sendErrorAndWait("您输入的结果不合法，请重新输入！");
        }
    }

    private static void sendErrorAndWait(String prompt) {
        System.err.println(prompt);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
