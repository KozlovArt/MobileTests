package config;

import org.aeonbits.owner.Config;

@Config.Sources(
        {"classpath:hosts/${deviceHost}.properties"}
)
public interface MobileConfig extends Config {
    @Key("deviceHost")
    @DefaultValue("browserstack")
    String getDeviceHost();

    @Key("deviceModel")
    @DefaultValue("Google Pixel 3")
    String getDeviceModel();

    @Key("systemVersion")
    @DefaultValue("9.0")
    String getSystemVersion();

    @Key("browserstackUser")
    @DefaultValue("bsuser_0DpZAV")
    String getBrowserstackUser();

    @Key("browserstackKey")
    @DefaultValue("26dchUaRjmryfKLCdp56")
    String getBrowserstackKey();

    @Key("browserstackApp")
    @DefaultValue("bs://e93a4f39691b59848c8de51621acc5df0a29d878")
    String getBrowserstackApp();

    @Key("appVersion")
    String getAppVersion();

    @Key("appUrl")
    String getAppUrl();

    @Key("appPackage")
    String getAppPackage();

    @Key("appActivity")
    String getAppActivity();
}
