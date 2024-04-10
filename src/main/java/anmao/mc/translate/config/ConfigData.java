package anmao.mc.translate.config;

public class ConfigData {
    private boolean onlineTranslate;
    private String defaultApiId;
    private long RequestInterval;

    public boolean isOnlineTranslate() {
        return onlineTranslate;
    }

    public String getDefaultApiId() {
        return defaultApiId;
    }

    public long getRequestInterval() {
        return RequestInterval;
    }
}
