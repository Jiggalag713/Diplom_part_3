import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationTest {
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
    public void registerTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.pressRegister();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.inputName("Петя");
        String randomEmail = "regdoll" + RandomStringUtils.randomAlphanumeric(12) + "@mail.ru";
        registerPage.inputEmail(randomEmail);
        registerPage.inputPassword("test123");
        registerPage.pressRegisterButton();
        loginPage.waitFormIsLoad();
        assertEquals("https://stellarburgers.nomoreparties.site/login", driver.getCurrentUrl());
    }

    @Test
    public void registerIncorrectPasswordTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.pressRegister();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.inputName("Петя");
        String randomEmail = "regdoll" + RandomStringUtils.randomAlphanumeric(12) + "@mail.ru";
        registerPage.inputEmail(randomEmail);
        registerPage.inputPassword("123");
        registerPage.pressRegisterButton();
        assertEquals(true, registerPage.passwordIsIncorrect());
    }
}
