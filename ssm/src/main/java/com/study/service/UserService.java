package com.study.service;

import com.study.entity.Student;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<Student> getStudentList();
}
