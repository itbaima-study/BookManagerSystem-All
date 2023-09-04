package com.test;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Book> LIST;

    public static void main(String[] args) {
        System.out.println("正在初始化图书管理系统...");
        load();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("=============== 图书管理系统 ===============");
            System.out.println("1. 录入书籍信息");
            System.out.println("2. 查阅书籍信息");
            System.out.println("3. 删除书籍信息");
            System.out.println("4. 修改书籍信息");
            System.out.println("5. 退出系统");
            System.out.println("======================================");

            switch (scanner.nextInt()) {
                case 1:
                    insert(scanner);
                    break;
                case 2:
                    list();
                    break;
                case 3:
                    delete(scanner);
                    break;
                case 4:
                    modify(scanner);
                    break;
                case 5:
                    System.out.println("正在保存图书数据...");
                    save();
                    System.out.println("感谢你的使用，再见！");
                    return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static void load(){
        File file = new File("data");
        if(file.exists()) {
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))){
                LIST = (List<Book>) stream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            LIST = new LinkedList<>();
        }
    }

    private static void save(){
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream("data"))){
            stream.writeObject(LIST);
            stream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void insert(Scanner scanner){
        scanner.nextLine();
        System.out.print("请输入书籍的标题：");
        String title = scanner.nextLine();
        System.out.print("请输入书籍的作者：");
        String author = scanner.nextLine();
        System.out.print("请输入书籍的价格：");
        int price = scanner.nextInt();
        scanner.nextLine();
        Book book = new Book(title, author, price);
        LIST.add(book);
        System.out.println("书籍信息添加成功："+book);
    }

    private static void modify(Scanner scanner){
        scanner.nextLine();
        System.out.print("请输入你要修改的书籍ID编号：");
        int index = scanner.nextInt();
        scanner.nextLine();
        while (index > LIST.size() - 1 || index < 0) {
            System.out.print("没有对应的书籍，请重新输入：");
            index = scanner.nextInt();
            scanner.nextLine();
        }

        Book book = LIST.get(index);
        System.out.print("请输入书籍的标题：");
        book.setTitle(scanner.nextLine());
        System.out.print("请输入书籍的作者：");
        book.setAuthor(scanner.nextLine());
        System.out.print("请输入书籍的价格：");
        book.setPrice(scanner.nextInt());
        scanner.nextLine();

        System.out.println("书籍信息修改成功！");
    }

    private static void list(){
        for (int i = 0; i < LIST.size(); i++) {
            System.out.println(i+"."+LIST.get(i));
        }
    }

    private static void delete(Scanner scanner){
        scanner.nextLine();
        System.out.print("请输入你要删除的书籍ID编号：");
        int index = scanner.nextInt();
        scanner.nextLine();
        while (index > LIST.size() - 1 || index < 0) {
            System.out.print("没有对应的书籍，请重新输入：");
            index = scanner.nextInt();
            scanner.nextLine();
        }
        LIST.remove(index);
        System.out.println("删除书籍信息成功！");
    }
}