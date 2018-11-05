package com.yxapp.webservice.push;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.base.payload.MultiMedia;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;

import java.util.ArrayList;
import java.util.List;

public class PushList {
	
	
	public static final String appId = "ElqXmwX8n08mUUiqAtepK7";
	public static final String appKey = "NS4Y8WtG0v5pB8nWRq83s6";
	public static final String masterSecret = "p9nWjBIAaV7N5Cw74FkLB5";
    public static final String CID = "c752f78d1dbc0a7877ceb526e3bb4792";
	
    static String host = "http://sdk.open.api.igexin.com/apiex.htm";
    public static void main(String[] args) throws Exception {
        // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        // 通知透传模板
        NotificationTemplate template = notificationTemplateDemo();
        TransmissionTemplate template2 = transmissionTemplateDemo();
        TransmissionTemplate iostemplate = getTemplate("11","222");
        ListMessage message = new ListMessage();
        message.setData(iostemplate);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List targets = new ArrayList();
        Target target1 = new Target();
        Target target2 = new Target();
        target1.setAppId(appId);
        target1.setClientId(CID);
//        target1.setAlias(Alias1);
//        target2.setAppId(appId);
//        target2.setClientId(CID2);
//        target2.setAlias(Alias2);
        targets.add(target1);
//        targets.add(target2);
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        System.out.println(ret.getResponse().toString());
    }
    
   /**
    * 推送给指定设备id
    * @param clientids 设备isd
    */
    public static String pushtoList(String title,String content,Object...clientids){
    	   // 配置返回每个用户返回用户状态，可选
        System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
        IGtPush push = new IGtPush(host, appKey, masterSecret);
        // 通知透传模板
        TransmissionTemplate iostemplate = getTemplate(title,content);
        ListMessage message = new ListMessage();
        message.setData(iostemplate);
        // 设置消息离线，并设置离线时间
        message.setOffline(true);
        // 离线有效时间，单位为毫秒，可选
        message.setOfflineExpireTime(24 * 1000 * 3600);
        // 配置推送目标
        List<Target> targets = new ArrayList<Target>();
        for (Object clientid : clientids) {
        	 Target target = new Target();
        	 target.setAppId(appId);
        	 target.setClientId((String)clientid);
        	 targets.add(target);
		}
        // taskId用于在推送时去查找对应的message
        String taskId = push.getContentId(message);
        IPushResult ret = push.pushMessageToList(taskId, targets);
        return ret.getResponse().toString();
    }
    /**
     * 通知模板
     * @return
     */
    public static NotificationTemplate notificationTemplateDemo() {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(appId);
        template.setAppkey(appKey);
        // 设置通知栏标题与内容
        template.setTitle("请输入通知栏标题");
        template.setText("请输入通知栏内容");
        // 配置通知栏图标
        template.setLogo("icon.png");
        // 配置通知栏网络图标
        template.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        template.setIsRing(true);
        template.setIsVibrate(true);
        template.setIsClearable(true);
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(1);
        template.setTransmissionContent("请输入您要透传的内容");
        return template;
    }
    /**
     * 透传模板
     * @return
     */
    public static TransmissionTemplate transmissionTemplateDemo() {
	    TransmissionTemplate template = new TransmissionTemplate();
	    template.setAppId(appId);
	    template.setAppkey(appKey);
	    // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
	    template.setTransmissionType(2);
	    template.setTransmissionContent("请输入需要透传的内容");
	    // 设置定时展示时间
	    // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
	    return template;
	}
    
    public static TransmissionTemplate getTemplate(String title,String content) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(appId);
        template.setAppkey(appKey);
        template.setTransmissionContent("{\"title\":\""+title+"\",\"content\":\""+content+"\",\"payload\":{\"islocal\":\"0\"}}");
        template.setTransmissionType(1);
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
     //   payload.setAutoBadge("+1");
        payload.setContentAvailable(0);
        payload.setSound("default");
        payload.setCategory("$由客户端定义");

        //简单模式APNPayload.SimpleMsg
       // payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));

        //字典模式使用APNPayload.DictionaryAlertMsg
        payload.setAlertMsg(getDictionaryAlertMsg(title,content));

        // 添加多媒体资源
        payload.addMultiMedia(new MultiMedia().setResType(MultiMedia.MediaType.video)
                    .setResUrl("http://ol5mrj259.bkt.clouddn.com/test2.mp4")
                    .setOnlyWifi(true));
   
        template.setAPNInfo(payload);
        return template;
    }
    private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(String title,String content){
        APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
        alertMsg.setBody(content);
        alertMsg.setActionLocKey("ActionLockey");
        alertMsg.setLocKey("LocKey");
        alertMsg.addLocArg("loc-args");
        alertMsg.setLaunchImage("launch-image");
        // iOS8.2以上版本支持
        alertMsg.setTitle(title);
        alertMsg.setTitleLocKey("TitleLocKey");
        alertMsg.addTitleLocArg("TitleLocArg");
        
        return alertMsg;
    }
}

