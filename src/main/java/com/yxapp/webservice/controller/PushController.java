package com.yxapp.webservice.controller;

import com.yxapp.webservice.service.push.PushService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PushController {

    private static final Log logger = LogFactory.getLog(PushController.class);

    @Autowired
    PushService pushService;

    @ResponseBody
    @PostMapping("/pushComment")
    public String pushComment(String title,String content,String[] clientids){
        try {

            String info = pushService.pushComment(title,content,clientids);
       //     logger.info("推送信息结果："+info);
        }catch (Exception e){
            logger.error(e.getMessage());
            return "fail";

        }
        return "success";
    }

    @ResponseBody
    @GetMapping("/pushComment")
    public String pushComment2(String title,String content,String[] clientids){

        try {

            String info = pushService.pushComment(title,content,clientids);
            logger.info("推送信息结果："+info);
        }catch (Exception e){
            logger.error(e.getMessage());
            return "fail";

        }
        return "success";
    }



}
