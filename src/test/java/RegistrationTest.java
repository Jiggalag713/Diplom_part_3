import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {
    private static final String BASE_URI = Constants.BASE_URI;
    private static final String EMAIL = Constants.EMAIL;
    private static final String PASSWORD = Constants.PASSWORD;
    private static final String NAME = Constants.NAME;
    private static final ApiHelper API = new ApiHelper(BASE_URI);
    private static WebDriver webdriver;
    private static Steps steps;
    private static LoginPage loginPage;

    @BeforeEach
    @Step("Стартуем браузер, настраиваем предусловия тестов")
    public void setUp() {
        webdriver = WebDriverFactory.getWebDriver(Browser.CHROME);
        webdriver.get(BASE_URI);
        steps = new Steps(API, webdriver);
        steps.setDriver(webdriver);
        loginPage = new LoginPage(webdriver);
    }

    @AfterEach
    @Step("Выходим из браузера, удаляем пользователя")
    public void tearDown() {
        if (webdriver != null) {
            webdriver.quit();
        }
    }

    @Test
    public void registerTest() {
        steps.createUser(EMAIL, PASSWORD, NAME);
        MainPage mainPage = new MainPage(webdriver);
        mainPage.pressLogin();
        loginPage.waitFormIsLoad();
        assertEquals(BASE_URI + "/login", webdriver.getCurrentUrl());
        steps.deleteUser(EMAIL, PASSWORD, NAME);
    }

    @Test
    public void registerIncorrectPasswordTest() {
        steps.registerUser(EMAIL, "123", NAME);
        assertEquals(true, steps.passwordIsIncorrect());
    }
}
