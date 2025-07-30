package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private final WebDriver driver;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    // Кнопка "Зарегистрироваться"
    private final By REGISTER_BUTTON = By.xpath(".//button[contains(text(), 'Зарегистрироваться')]");

    // Ссылка "Войти"
    private final By LOGIN_LINK = By.xpath(".//a[contains(text(), 'Войти')]");

    // Поле ввода "Имя"
    private final By NAME_INPUT = By.xpath(".//label[contains(text(), 'Имя')]/parent::div/input");

    // Поле ввода "Email"
    private final By EMAIL_INPUT = By.xpath(".//label[contains(text(), 'Email')]/parent::div/input");

    // Поле ввода "Пароль"
    private final By PASSWORD_INPUT = By.xpath(".//label[contains(text(), 'Пароль')]/parent::div/input");

    // Заполнить поле "Имя"
    public void inputName(String name) {
        driver.findElement(NAME_INPUT).sendKeys(name);
    }

    // Заполнить поле "Email"
    public void inputEmail(String email) {
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    // Заполнить поле "Пароль"
    public void inputPassword(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    // Нажать кнопку "Зарегистрироваться"
    public void pressRegisterButton() {
        driver.findElement(REGISTER_BUTTON).click();
    }

    // Нажать ссылку "Войти"
    public void pressLoginLink() {
        driver.findElement(LOGIN_LINK).click();
    }

    // Сообщение об ошибке "Некорректный пароль"
    private final By INCORRECT_PASSWORD_MESSAGE = By.xpath(".//p[contains(text(), 'Некорректный пароль')]");

    // Отображается ли сообщение о некорректном пароле
    public Boolean passwordIsIncorrect() {
        return driver.findElement(INCORRECT_PASSWORD_MESSAGE).isDisplayed();
    }
}
