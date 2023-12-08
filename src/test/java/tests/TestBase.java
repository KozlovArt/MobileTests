package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import config.MobileConfig;
import drivers.BrowserstackDriver;
import drivers.LocalDriver;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

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
        //SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    void addAttachments() {
        closeWebDriver();
        /*String sessionId = Selenide.sessionId().toString();
        System.out.println(sessionId);

        Attach.pageSource();
        closeWebDriver();


        Attach.addVideo(sessionId);*/
    }
}
