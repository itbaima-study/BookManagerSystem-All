package com.test.service;

import com.test.entity.Student;
import com.test.mapper.StudentMapper;
import com.test.util.InputUtil;
import com.test.util.SqlUtil;
import lombok.extern.java.Log;

import java.util.List;

@Log
public class StudentService {

    public static void addStudent() {
        String name = InputUtil.nextLine("请输入需要插入的学生名称: ");
        String gender = InputUtil.nextLine("请输入需要插入的学生性别(男/女): ", "男", "女");
        int age = InputUtil.nextInt("请输入需要插入的学生年龄: ");
        Student student = new Student(0, name, gender, age);

        SqlUtil.doSqlWork(StudentMapper.class, mapper -> {
            int count = mapper.insertStudent(student);
            if(count > 0) {
                System.out.println("用户信息插入成功: " + student);
                log.info("用户信息插入操作" + student);
            } else {
                System.err.println("用户信息插入失败，请联系管理员！");
            }
        });
    }

    public static void listStudent() {
        SqlUtil.doSqlWork(StudentMapper.class, mapper -> {
            List<Student> students = mapper.selectAllStudent();
            if(!students.isEmpty()) {
                String format = "%-3s %-6s %-5s %-5s\n";
                System.out.printf(format, "学号", "姓名", "性别", "年龄");
                students.forEach(student -> System.out.printf(format, student.getSid(),
                        student.getName(), student.getGender(), student.getAge()));
            } else {
                System.err.println("系统中没有任何学生信息");
            }
        });
    }
}
