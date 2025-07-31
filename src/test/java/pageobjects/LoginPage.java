package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // Зарегистрироваться
    private final By REGISTER_LINK = By.className("Auth_link__1fOlj");

    // Ссылка "Восстановить пароль"
    private final By RESTORE_PASSWORD_LINK = By.xpath(".//a[contains(text(), 'Восстановить пароль')]");

    // Нажать линк "Зарегистрироваться"
    public void pressRegister() {
        driver.findElement(REGISTER_LINK).click();
    }

    // Нажать линк "Восстановить пароль"
    public void pressRestorePasswordLink() {
        driver.findElement(RESTORE_PASSWORD_LINK).click();
    }

    // Поле Email
    public final By EMAIL_INPUT = By.xpath(".//label[contains(text(), 'Email')]/parent::div/input");

    // Поле Пароль
    private final By PASSWORD_INPUT = By.xpath(".//label[contains(text(), 'Пароль')]/parent::div/input");

    // Заполнить поле "Email"
    public void inputEmail(String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    // Заполнить поле "Пароль"
    public void inputPassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    // Кнопка "Войти"
    private final By LOGIN_BUTTON = By.xpath(".//button[contains(text(), 'Войти')]");

    // Нажать кнопку "Войти"
    public void pressLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void waitFormIsLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON));
    }
}
