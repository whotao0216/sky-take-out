package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;

@Mapper
public interface UserMapper {

    @Select("select *from sky_take_out.user where openid=#{openid}")
    User getByOpenid(String openid);

    Integer addUser(User user);

    @Select("select *from sky_take_out.user where user.id=#{userId}")
    User getById(Long userId);

    Long sumByTime(LocalDateTime begin, LocalDateTime end);

}
