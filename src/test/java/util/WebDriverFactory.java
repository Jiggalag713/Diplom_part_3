package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
    public static WebDriver getWebDriver(Browser browser) {
        switch (browser) {
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "drivers/chromedriver");
                return new ChromeDriver();
            case YANDEX:
                System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver");
                return new ChromeDriver();
            default: return new ChromeDriver();
        }
    }
}
