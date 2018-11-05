package com.yxapp.webservice.dao.mapper;

import com.yxapp.webservice.entity.PushMessage;
import com.yxapp.webservice.entity.PushMessageVo;

import java.util.List;

public interface PushMessageMapper {
    int deleteByPrimaryKey(String pushid);

    int insert(PushMessage record);

    int insertSelective(PushMessage record);

    PushMessage selectByPrimaryKey(String pushid);

    int updateByPrimaryKeySelective(PushMessage record);

    int updateByPrimaryKey(PushMessage record);

    List<String> queryClientidByUserid(PushMessageVo ushMessageVo);
}