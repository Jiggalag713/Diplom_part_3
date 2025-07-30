import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.LoginPage;
import pageobjects.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTest {
    private WebDriver driver;

    @BeforeEach
    @Step("Стартуем браузер")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Создание драйвера перед каждым тестом
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @AfterEach
    @Step("Выходим из браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void bunSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitFormIsLoad();
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        mainPage.pressSauces();
        mainPage.pressBuns();
        assertTrue(mainPage.isSectionSelected("Булки"));
    }

    @Test
    public void sauceSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitFormIsLoad();
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        mainPage.pressSauces();
        assertTrue(mainPage.isSectionSelected("Соусы"));
    }

    @Test
    public void fillingSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitFormIsLoad();
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        mainPage.pressFillings();
        assertTrue(mainPage.isSectionSelected("Начинки"));
    }
}
