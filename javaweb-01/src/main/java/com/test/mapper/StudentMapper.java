package com.test.mapper;

import com.test.entity.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StudentMapper {

    @Insert("insert into db_student (name, gender, age) values(#{name}, #{gender}, #{age})")
    int insertStudent(Student student);

    @Select("select * from db_student")
    List<Student> selectAllStudent();

    @Select("select * from db_student where sid = #{sid}")
    Student selectStudentBySid(int sid);
}
