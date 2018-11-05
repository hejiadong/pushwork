package com.yxapp.webservice.service.push.impl;

import com.yxapp.webservice.dao.mapper.PushMessageMapper;
import com.yxapp.webservice.dao.mapper.UserMapper;
import com.yxapp.webservice.push.PushList;
import com.yxapp.webservice.service.push.PushService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PushServiceImpl  implements PushService{

    private static final Log logger = LogFactory.getLog(PushServiceImpl.class);
//    @Autowired
//    PushMessageMapper pushMessageMapper;
//
//    @Autowired
//    UserMapper userMapper;
    //推送评论
    @Override
    public String pushComment(String title, String content,Object...clientids) throws Exception {

//        PushMessageVo pushMessageVo = new PushMessageVo();
//        pushMessageVo.setUseridList(Arrays.asList(pushdestUserids));
//        //根据userid查询出所有要推送的cliendid
//        List<String> cliendidList = pushMessageMapper.queryClientidByUserid(pushMessageVo);
//        if(cliendidList.size()==0){
//            return;
//        }
        if(clientids==null || clientids.length==0){
            return "0";
        }

        //推送信息
        String info =  PushList.pushtoList(title,content,clientids);
        return info;




    }






}
