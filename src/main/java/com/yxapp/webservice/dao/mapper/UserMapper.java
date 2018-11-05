package com.yxapp.webservice.dao.mapper;

import com.yxapp.webservice.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String usrId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String usrId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}