package com.test.util;

import lombok.extern.java.Log;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.util.function.Consumer;

@Log
public class SqlUtil {
    private static SqlSession session;

    static {
        try {
            SqlSessionFactory factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
            session = factory.openSession(true);
        } catch (IOException e) {
            log.warning("MyBatis初始化失败: " + e.getMessage());
        }
    }

    public static <T> void doSqlWork(Class<T> mapperClass, Consumer<T> consumer) {
        consumer.accept(takeMapper(mapperClass));
    }

    public static <T> T takeMapper(Class<T> mapperClass) {
        return session.getMapper(mapperClass);
    }
}
