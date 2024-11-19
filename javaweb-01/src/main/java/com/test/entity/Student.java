package com.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    int sid;
    String name;
    String gender;
    int age;
}
