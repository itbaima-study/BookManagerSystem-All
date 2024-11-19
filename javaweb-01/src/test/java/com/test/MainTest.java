package com.test;

import com.test.mapper.BookMapper;
import com.test.mapper.BorrowMapper;
import com.test.mapper.StudentMapper;
import com.test.util.SqlUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MainTest {

    @Test
    @DisplayName("测试学生表读取操作")
    public void selectStudent() {
        SqlUtil.doSqlWork(StudentMapper.class, StudentMapper::selectAllStudent);
    }

    @Test
    @DisplayName("测试书籍表读取操作")
    public void selectBook() {
        SqlUtil.doSqlWork(BookMapper.class, BookMapper::selectAllBook);
    }

    @Test
    @DisplayName("测试借阅表读取操作")
    public void selectBorrow() {
        SqlUtil.doSqlWork(BorrowMapper.class, BorrowMapper::selectAllBorrow);
    }
}
