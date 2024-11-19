package com.test;

import com.test.service.BookService;
import com.test.service.BorrowService;
import com.test.service.StudentService;
import com.test.util.InputUtil;
import org.apache.ibatis.io.Resources;

import java.io.IOException;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    private static final String prompt = """
            ======== 图书管理系统 ========
            1. 录入学生信息
            2. 录入书籍信息
            3. 录入借阅信息
            4. 查询学生信息
            5. 查询书籍信息
            6. 查询借阅情况
            请输入上面功能对应的序号（输入其他数字）
            """;

    public static void main(String[] args) throws IOException {
        LogManager.getLogManager().readConfiguration(Resources.getResourceAsStream("logging.properties"));

        while (true) {
            int index = InputUtil.nextInt(prompt);
            if(index <= 6 && index >= 1) {
                switch (index) {
                    case 1 -> StudentService.addStudent();
                    case 2 -> BookService.addBook();
                    case 3 -> BorrowService.addBorrow();
                    case 4 -> StudentService.listStudent();
                    case 5 -> BookService.listBook();
                    case 6 -> BorrowService.listBorrow();
                }
            } else {
                break;
            }
        }
        System.out.println("再见，欢迎您再次使用本系统~");
    }
}
