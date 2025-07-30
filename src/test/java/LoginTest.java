import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegisterPage;
import pageobjects.RestorePasswordPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    private WebDriver driver;
    Actions actions;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // Создание драйвера перед каждым тестом
        actions = new Actions(driver);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    public void loginFromMainPageTest() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        assertEquals(true, mainPage.isCreateOrderButtonVisible());
    }

    @Test
    public void loginFromAccountButton() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.pressPersonalAccount();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        assertEquals(true, mainPage.isCreateOrderButtonVisible());
    }

    @Test
    public void loginFromRegistrationForm() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.pressRegister();
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.pressLoginLink();
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        assertEquals(true, mainPage.isCreateOrderButtonVisible());
    }

    @Test
    public void loginFromPasswordRecoveryForm() {
        MainPage mainPage = new MainPage(driver, actions);
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitFormIsLoad();
        loginPage.pressRestorePasswordLink();
        RestorePasswordPage restorePasswordPage = new RestorePasswordPage(driver);
        restorePasswordPage.pressLoginLink();
        loginPage.waitFormIsLoad();
        loginPage.inputEmail("regdoll@mail.ru");
        loginPage.inputPassword("test123");
        loginPage.pressLoginButton();
        mainPage.waitFormIsLoad();
        assertEquals(true, mainPage.isCreateOrderButtonVisible());
    }
}
