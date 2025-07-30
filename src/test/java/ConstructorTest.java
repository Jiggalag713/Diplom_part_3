import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import pageobjects.LoginPage;
import pageobjects.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTest {
    /*Раздел «Конструктор»
    Проверь, что работают переходы к разделам:
    «Булки»,
    «Соусы»,
    «Начинки».
    */
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
    public void bunSectionTest() {
        MainPage mainPage = new MainPage(driver, actions);
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
        MainPage mainPage = new MainPage(driver, actions);
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
        MainPage mainPage = new MainPage(driver, actions);
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
