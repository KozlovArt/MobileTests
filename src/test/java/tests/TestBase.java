package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.MobileConfig;
import drivers.BrowserstackDriver;
import drivers.LocalDriver;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.id;

public class TestBase {
    static MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());
    @BeforeAll
    static void beforeAll() {
        switch (config.getDeviceHost()){
            case "emulation" :
                Configuration.browser = LocalDriver.class.getName();
                break;
            case "browserstack" :
                Configuration.browser = BrowserstackDriver.class.getName();
                break;
        }

        Configuration.browserSize = null;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    void beforeEach() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        String sessionId = Selenide.sessionId().toString();
        if (config.getDeviceHost().equals("emulation")) {
            Attach.screenshotAs("Last screenshot");
        }
        Attach.pageSource();
        closeWebDriver();
        if (config.getDeviceHost().equals("browserstack")) {
            Attach.addVideo(sessionId);
        }
    }
}
