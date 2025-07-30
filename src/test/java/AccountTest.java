import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pageobjects.AccountPage;
import pageobjects.LoginPage;
import pageobjects.MainPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest {
    private WebDriver driver;
    Actions actions;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Создание драйвера перед каждым тестом
        actions = new Actions(driver);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @AfterEach
    public void tearDown() {
        // TODO: add
    }

    @Test
    public void accountTest() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        mainPage.pressPersonalAccount();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.waitFormIsLoad();
        assertEquals("https://stellarburgers.nomoreparties.site/account/profile", driver.getCurrentUrl());
    }

    @Test
    public void clickConstructorTest() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        mainPage.pressConstructorLink();
        assertEquals("https://stellarburgers.nomoreparties.site/", driver.getCurrentUrl());
    }

    @Test
    public void clickLogoTest() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        mainPage.pressLogo();
        assertEquals("https://stellarburgers.nomoreparties.site/", driver.getCurrentUrl());
    }

    @Test
    public void logoutTest() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        mainPage.pressPersonalAccount();
        AccountPage accountPage = new AccountPage(driver);
        accountPage.waitFormIsLoad();
        accountPage.pressLogout();
        loginPage.waitFormIsLoad();
        assertEquals("https://stellarburgers.nomoreparties.site/login", driver.getCurrentUrl());
    }
}
