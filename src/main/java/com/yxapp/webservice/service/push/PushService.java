package com.yxapp.webservice.service.push;

public interface PushService {

    public String pushComment(String title, String content, Object... clientids) throws Exception;
}
