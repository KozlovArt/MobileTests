package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;


public class MobileTests extends TestBase {
    @Test
    void successfulSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("Verify content found", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    void openArticleTest() {
        back();
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });

        step("Open article", () -> {
        $$(id("org.wikipedia.alpha:id/page_list_item_description")).get(0).click();
        if (config.getDeviceHost().equals("emulation")) {
            $(id("org.wikipedia.alpha:id/page_web_view")).click();
        }
        });

        step("Check article", () -> {
        $$(className("android.webkit.WebView")).shouldHave(itemWithText("Appium"));
        });
    }

    @Test
    void onboardingScreenTest () {

        step("Check first screen", () -> {
        $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("in over 300 languages"));
        $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Check second screen", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("New ways to explore"));
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Check third screen", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Reading lists with sync"));
            $(id("org.wikipedia.alpha:id/fragment_onboarding_forward_button")).click();
        });

        step("Check fourth screen", () -> {
            $(id("org.wikipedia.alpha:id/primaryTextView")).shouldHave(text("Send anonymous data"));
            $(id("org.wikipedia.alpha:id/acceptButton")).click();
        });
    }
}
