import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.AccountPage;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    private static final String BASE_URI = Constants.BASE_URI;
    private static final String EMAIL = Constants.EMAIL;
    private static final String PASSWORD = Constants.PASSWORD;
    private static final String NAME = Constants.NAME;
    private static final ApiHelper API = new ApiHelper(BASE_URI);
    private WebDriver webdriver;
    private static Steps steps;
    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static AccountPage accountPage;

    @BeforeEach
    @Step("Стартуем браузер, настраиваем предусловия тестов")
    public void setUp() {
        webdriver = WebDriverFactory.getWebDriver(Browser.CHROME);
        webdriver.get(BASE_URI);
        steps = new Steps(API, webdriver);
        steps.setDriver(webdriver);
        mainPage = new MainPage(webdriver);
        loginPage = new LoginPage(webdriver);
        accountPage = new AccountPage(webdriver);
        steps.createUser(EMAIL, PASSWORD, NAME);
        mainPage.pressLogin();
        loginPage.waitFormIsLoad();
        steps.loginUser(EMAIL, PASSWORD);
        mainPage.waitFormIsLoad();
    }

    @AfterEach
    @Step("Выходим из браузера, удаляем пользователя")
    public void tearDown() {
        steps.deleteUser(EMAIL, PASSWORD, NAME);
        if (webdriver != null) {
            webdriver.quit();
        }
    }

    @Test
    public void accountTest() {
        mainPage.pressPersonalAccount();
        accountPage.waitFormIsLoad();
        assertEquals(BASE_URI + "/account/profile", webdriver.getCurrentUrl());
    }

    @Test
    public void clickConstructorTest() {
        mainPage.pressConstructorLink();
        assertEquals(BASE_URI + "/", webdriver.getCurrentUrl());
    }

    @Test
    public void clickLogoTest() {
        mainPage.pressLogo();
        assertEquals(BASE_URI + "/", webdriver.getCurrentUrl());
    }

    @Test
    public void logoutTest() {
        mainPage.pressPersonalAccount();
        accountPage.waitFormIsLoad();
        accountPage.pressLogout();
        loginPage.waitFormIsLoad();
        assertEquals(BASE_URI + "/login", webdriver.getCurrentUrl());
    }
}
