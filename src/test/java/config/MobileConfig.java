package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:devices/${device}.properties",
})
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
}
