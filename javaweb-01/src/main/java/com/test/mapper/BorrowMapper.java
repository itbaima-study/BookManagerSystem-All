package com.test.mapper;

import com.test.entity.Borrow;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BorrowMapper {

    @Insert("insert into db_borrow (sid, bid) values(#{sid}, #{bid})")
    int insertBorrow(@Param("sid") int sid, @Param("bid") int bid);

    @Results({
            @Result(column = "sid", property = "student",
                    one = @One(select = "com.test.mapper.StudentMapper.selectStudentBySid")),
            @Result(column = "bid", property = "book",
                    one = @One(select = "com.test.mapper.BookMapper.selectBookByBid"))
    })
    @Select("select * from db_borrow")
    List<Borrow> selectAllBorrow();
}
