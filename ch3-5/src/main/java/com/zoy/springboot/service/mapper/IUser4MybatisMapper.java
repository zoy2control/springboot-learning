package com.zoy.springboot.service.mapper;

import com.zoy.springboot.service.model.User4Mybatis;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by zouzp on 2019/2/13.
 */
@Mapper
public interface IUser4MybatisMapper {
    /**
     * ·根据 name查找 User
     * @param name
     * @return
     */
    @Select("SELECT * FROM user_info WHERE name = #{name}")
    User4Mybatis findByName(@Param("name") String name);

    /**
     * ·插入数据
     * @param name
     * @param age
     * @return
     */
    @Insert("INSERT INTO user_info(name, age) VALUES (#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);
}
