package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.MobileConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackDriver implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        MutableCapabilities caps = new MutableCapabilities();
        MobileConfig config = ConfigFactory.create(MobileConfig.class, System.getProperties());

        // Set your access credentials
        caps.setCapability("browserstack.user", "bsuser_0DpZAV");
        caps.setCapability("browserstack.key", "26dchUaRjmryfKLCdp56");

        // Set URL of the application under test
        caps.setCapability("app", "bs://e93a4f39691b59848c8de51621acc5df0a29d878");

        // Specify device and os_version for testing
        caps.setCapability("device", config.getDeviceModel());
        caps.setCapability("os_version", config.getSystemVersion());

        // Set other BrowserStack capabilities
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        try {
            return new RemoteWebDriver(
                    new URL("https://hub.browserstack.com/wd/hub"), caps);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}