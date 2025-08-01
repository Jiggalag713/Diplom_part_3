import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegisterPage;
import pageobjects.RestorePasswordPage;
import util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {
    private static final String BASE_URI = Constants.BASE_URI;
    private static final String EMAIL = Constants.EMAIL;
    private static final String PASSWORD = Constants.PASSWORD;
    private static final String NAME = Constants.NAME;
    private static final ApiHelper API = new ApiHelper(BASE_URI);
    private static WebDriver webdriver;
    private static Steps steps;
    private static MainPage mainPage;
    private static LoginPage loginPage;
    private static RegisterPage registerPage;
    private static RestorePasswordPage restorePasswordPage;

    @BeforeEach
    @Step("Стартуем браузер, настраиваем предусловия тестов")
    public void setUp() {
        webdriver = WebDriverFactory.getWebDriver(Browser.CHROME);
        webdriver.get(BASE_URI);
        steps = new Steps(API, webdriver);
        steps.setDriver(webdriver);
        mainPage = new MainPage(webdriver);
        loginPage = new LoginPage(webdriver);
        registerPage = new RegisterPage(webdriver);
        restorePasswordPage = new RestorePasswordPage(webdriver);
        steps.createUser(EMAIL, PASSWORD, NAME);
        mainPage.pressLogin();
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
    public void loginFromMainPageTest() {
        loginPage.waitFormIsLoad();
        steps.loginUser(EMAIL, PASSWORD);
        mainPage.waitFormIsLoad();
        assertEquals(true, mainPage.isCreateOrderButtonVisible());
    }

    @Test
    public void loginFromAccountButton() {
        mainPage.pressPersonalAccount();
        loginPage.waitFormIsLoad();
        steps.loginUser(EMAIL, PASSWORD);
        mainPage.waitFormIsLoad();
        assertEquals(true, mainPage.isCreateOrderButtonVisible());
    }

    @Test
    public void loginFromRegistrationForm() {
        loginPage.waitFormIsLoad();
        loginPage.pressRegister();
        registerPage.pressLoginLink();
        loginPage.waitFormIsLoad();
        steps.loginUser(EMAIL, PASSWORD);
        mainPage.waitFormIsLoad();
        assertEquals(true, mainPage.isCreateOrderButtonVisible());
    }

    @Test
    public void loginFromPasswordRecoveryForm() {
        loginPage.waitFormIsLoad();
        loginPage.pressRestorePasswordLink();
        restorePasswordPage.pressLoginLink();
        steps.loginUser(EMAIL, PASSWORD);
        mainPage.waitFormIsLoad();
        assertEquals(true, mainPage.isCreateOrderButtonVisible());
    }
}
