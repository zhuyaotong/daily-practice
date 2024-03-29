package com.github.zhuyaotong.designpatterns.demo;

import java.util.ArrayList;
import java.util.List;

//public class Demo {
//}
//
//public interface ApiAuthenticator {
//    void auth(String url);
//    void auth(ApiRequest apiRequest);
//}
//
//public class DefaultApiAuthenticatorImpl implements ApiAuthenticator {
//    private CredentialStorage credentialStorage;
//
//    public DefaultApiAuthenticatorImpl() {
//        this.credentialStorage = new MysqlCredentialStorage();
//    }
//
//    public DefaultApiAuthenticatorImpl(CredentialStorage credentialStorage) {
//        this.credentialStorage = credentialStorage;
//    }
//
//    @Override
//    public void auth(String url) {
//        ApiRequest apiRequest = ApiRequest.buildFromUrl(url);
//        auth(apiRequest);
//    }
//
//    @Override
//    public void auth(ApiRequest apiRequest) {
//        String appId = apiRequest.getAppId();
//        String token = apiRequest.getToken();
//        long timestamp = apiRequest.getTimestamp();
//        String originalUrl = apiRequest.getOriginalUrl();
//
//        AuthToken clientAuthToken = new AuthToken(token, timestamp);
//        if (clientAuthToken.isExpired()) {
//            throw new RuntimeException("Token is expired.");
//        }
//
//        String password = credentialStorage.getPasswordByAppId(appId);
//        AuthToken serverAuthToken = AuthToken.generate(originalUrl, appId, password, timestamp);
//        if (!serverAuthToken.match(clientAuthToken)) {
//            throw new RuntimeException("Token verfication failed.");
//        }
//    }
//}
//
//
//public class Alert {
//    private AlertRule rule;
//    private Notification notification;
//
//    public Alert(AlertRule rule, Notification notification) {
//        this.rule = rule;
//        this.notification = notification;
//    }
//
//    public void check(String api, long requestCount, long errorCount, long durationOfSeconds) {
//        long tps = requestCount / durationOfSeconds;
//        if (tps > rule.getMatchedRule(api).getMaxTps()) {
//            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
//        }
//        if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
//            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
//        }
//    }
//}
//
//
//public class Alert {
//    // ...省略AlertRule/Notification属性和构造函数...
//
//    // 改动一：添加参数timeoutCount
//    public void check(String api, long requestCount, long errorCount, long timeoutCount, long durationOfSeconds) {
//        long tps = requestCount / durationOfSeconds;
//        if (tps > rule.getMatchedRule(api).getMaxTps()) {
//            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
//        }
//        if (errorCount > rule.getMatchedRule(api).getMaxErrorCount()) {
//            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
//        }
//        // 改动二：添加接口超时处理逻辑
//        long timeoutTps = timeoutCount / durationOfSeconds;
//        if (timeoutTps > rule.getMatchedRule(api).getMaxTimeoutTps()) {
//            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
//        }
//    }
//}
//
//
//public class Alert {
//    private List<AlertHandler> alertHandlers = new ArrayList<>();
//
//    public void addAlertHandler(AlertHandler alertHandler) {
//        this.alertHandlers.add(alertHandler);
//    }
//
//    public void check(ApiStatInfo apiStatInfo) {
//        for (AlertHandler handler : alertHandlers) {
//            handler.check(apiStatInfo);
//        }
//    }
//}
//
//public class ApiStatInfo {//省略constructor/getter/setter方法
//    private String api;
//    private long requestCount;
//    private long errorCount;
//    private long durationOfSeconds;
//}
//
//public abstract class AlertHandler {
//    protected AlertRule rule;
//    protected Notification notification;
//    public AlertHandler(AlertRule rule, Notification notification) {
//        this.rule = rule;
//        this.notification = notification;
//    }
//    public abstract void check(ApiStatInfo apiStatInfo);
//}
//
//public class TpsAlertHandler extends AlertHandler {
//    public TpsAlertHandler(AlertRule rule, Notification notification) {
//        super(rule, notification);
//    }
//
//    @Override
//    public void check(ApiStatInfo apiStatInfo) {
//        long tps = apiStatInfo.getRequestCount()/ apiStatInfo.getDurationOfSeconds();
//        if (tps > rule.getMatchedRule(apiStatInfo.getApi()).getMaxTps()) {
//            notification.notify(NotificationEmergencyLevel.URGENCY, "...");
//        }
//    }
//}
//
//public class ErrorAlertHandler extends AlertHandler {
//    public ErrorAlertHandler(AlertRule rule, Notification notification){
//        super(rule, notification);
//    }
//
//    @Override
//    public void check(ApiStatInfo apiStatInfo) {
//        if (apiStatInfo.getErrorCount() > rule.getMatchedRule(apiStatInfo.getApi()).getMaxErrorCount()) {
//            notification.notify(NotificationEmergencyLevel.SEVERE, "...");
//        }
//    }
//}
//
//
//public class ApplicationContext {
//    private AlertRule alertRule;
//    private Notification notification;
//    private Alert alert;
//
//    public void initializeBeans() {
//        alertRule = new AlertRule(/*.省略参数.*/); //省略一些初始化代码
//        notification = new Notification(/*.省略参数.*/); //省略一些初始化代码
//        alert = new Alert();
//        alert.addAlertHandler(new TpsAlertHandler(alertRule, notification));
//        alert.addAlertHandler(new ErrorAlertHandler(alertRule, notification));
//    }
//    public Alert getAlert() { return alert; }
//
//    // 饿汉式单例
//    private static final ApplicationContext instance = new ApplicationContext();
//    private ApplicationContext() {
//        initializeBeans();
//    }
//    public static ApplicationContext getInstance() {
//        return instance;
//    }
//}
//
//public class Demo {
//    public static void main(String[] args) {
//        ApiStatInfo apiStatInfo = new ApiStatInfo();
//        // ...省略设置apiStatInfo数据值的代码
//        ApplicationContext.getInstance().getAlert().check(apiStatInfo);
//    }
//}
