import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import util.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTest {
    private static final String BASE_URI = Constants.BASE_URI;
    private static final String EMAIL = Constants.EMAIL;
    private static final String PASSWORD = Constants.PASSWORD;
    private static final String NAME = Constants.NAME;
    private static final ApiHelper API = new ApiHelper(BASE_URI);
    private WebDriver webdriver;
    private static Steps steps;
    private static MainPage mainPage;

    @BeforeEach
    @Step("Стартуем браузер, настраиваем предусловия тестов")
    public void setUp() {
        webdriver = WebDriverFactory.getWebDriver(Browser.CHROME);
        webdriver.get(BASE_URI);
        steps = new Steps(API, webdriver);
        steps.setDriver(webdriver);
        mainPage = new MainPage(webdriver);
        LoginPage loginPage = new LoginPage(webdriver);
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
    public void bunSectionTest() {
        mainPage.pressSauces();
        mainPage.pressBuns();
        assertTrue(mainPage.isSectionSelected("Булки"));
    }

    @Test
    public void sauceSectionTest() {
        mainPage.pressSauces();
        assertTrue(mainPage.isSectionSelected("Соусы"));
    }

    @Test
    public void fillingSectionTest() {
        mainPage.pressFillings();
        assertTrue(mainPage.isSectionSelected("Начинки"));
    }
}
