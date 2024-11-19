package com.test.service;

import com.test.entity.Book;
import com.test.entity.Borrow;
import com.test.entity.Student;
import com.test.mapper.BookMapper;
import com.test.mapper.BorrowMapper;
import com.test.mapper.StudentMapper;
import com.test.util.InputUtil;
import com.test.util.SqlUtil;
import lombok.extern.java.Log;

import java.util.List;

@Log
public class BorrowService {

    public static void addBorrow() {
        StudentMapper studentMapper = SqlUtil.takeMapper(StudentMapper.class);
        BookMapper bookMapper = SqlUtil.takeMapper(BookMapper.class);

        Student student = InputUtil.nextEntity("请输入借阅人学号: ", studentMapper::selectStudentBySid);
        Book book = InputUtil.nextEntity("请输入借阅书籍编号: ", bookMapper::selectBookByBid);

        SqlUtil.doSqlWork(BorrowMapper.class, mapper -> {
            int count = mapper.insertBorrow(student.getSid(), book.getBid());
            if(count > 0) {
                System.out.println("用户信息插入成功: " + student.getName() + " 借阅了 " + book.getTitle());
                log.info("借阅信息插入操作" + student.getName() + " 借阅了 " + book.getTitle());
            } else {
                System.err.println("用户信息插入失败，请联系管理员！");
            }
        });
    }

    public static void listBorrow() {
        SqlUtil.doSqlWork(BorrowMapper.class, mapper -> {
            List<Borrow> borrows = mapper.selectAllBorrow();
            if(!borrows.isEmpty()) {
                String format = "%-3s %-6s %-3s %20s\n";
                System.out.printf(format, "学号", "姓名", "书籍编号", "书籍名称");
                borrows.forEach(borrow -> System.out.printf(format,
                        borrow.getStudent().getSid(), borrow.getStudent().getName(),
                        borrow.getBook().getBid(), borrow.getBook().getTitle()));
            } else {
                System.err.println("系统中没有任何借阅信息");
            }
        });
    }
}
