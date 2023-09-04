package com.study.mapper;

import com.study.entity.Account;
import com.study.entity.Student;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("select * from user where username = #{username}")
    Account findUserByName(String username);

    @Select("select * from student")
    List<Student> getStudentList();
}
