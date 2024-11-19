package com.test.service;

import com.test.entity.Book;
import com.test.mapper.BookMapper;
import com.test.util.InputUtil;
import com.test.util.SqlUtil;
import lombok.extern.java.Log;

import java.util.List;

@Log
public class BookService {

    public static void addBook() {
        String title = InputUtil.nextLine("请输入需要插入的书籍名称: ");
        String desc = InputUtil.nextLine("请输入需要插入的书籍简介: ");
        Book book = new Book(0, title, desc);

        SqlUtil.doSqlWork(BookMapper.class, mapper -> {
            int count = mapper.insertBook(book);
            if(count > 0) {
                System.out.println("图书信息插入成功: " + book);
                log.info("图书信息插入操作" + book);
            } else {
                System.err.println("图书信息插入失败，请联系管理员！");
            }
        });
    }

    public static void listBook() {
        SqlUtil.doSqlWork(BookMapper.class, mapper -> {
            List<Book> books = mapper.selectAllBook();
            if(!books.isEmpty()) {
                String format = "%-3s %-10s %s\n";
                System.out.printf(format, "编号", "标题", "简介");
                books.forEach(book -> System.out.printf(format,
                        book.getBid(), book.getTitle(), book.getDescription()));
            } else {
                System.err.println("系统中没有任何学生信息");
            }
        });
    }
}
