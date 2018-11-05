package com.yxapp.webservice.entity;

import java.util.List;

public class PushMessageVo {

    //推送消息实体类
    private PushMessage pushMessage;

    private List<PushMessage> pushMessageList;

    private List<Object> useridList;

    public PushMessage getPushMessage() {
        return pushMessage;
    }

    public void setPushMessage(PushMessage pushMessage) {
        this.pushMessage = pushMessage;
    }

    public List<PushMessage> getPushMessageList() {
        return pushMessageList;
    }

    public void setPushMessageList(List<PushMessage> pushMessageList) {
        this.pushMessageList = pushMessageList;
    }

    public List<Object> getUseridList() {
        return useridList;
    }

    public void setUseridList(List<Object> useridList) {
        this.useridList = useridList;
    }
}
