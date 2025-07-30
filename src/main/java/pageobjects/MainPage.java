package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final Actions actions;

    public MainPage(WebDriver driver, Actions actions){
        this.driver = driver;
        this.actions = actions;
    }

    // Кнопка "Войти в аккаунт"
    private final By LOGIN_BUTTON = By.xpath(".//button[contains(text(),'Войти в аккаунт')]");

    // Кнопка "Оформить заказ"
    private final By CREATE_ORDER = By.xpath(".//button[contains(text(), 'Оформить заказ')]");

    // Кнопка "Личный кабинет"
    private final By PERSONAL_ACCOUNT = By.xpath(".//p[contains(text(), 'Личный Кабинет')]");

    // Ссылка "Конструктор"
    private final By CONSTRUCTOR_LINK = By.xpath(".//p[contains(text(), 'Конструктор')]");

    // Конструктор
    private static final By BURGER_CONSTRUCTOR = By.xpath(".//div[starts-with(@class, 'BurgerIngredients')]");

    // Лого
    private final By LOGO = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");

    // Секция "Булки"
    public static final By BUNS_BUTTON = By.xpath(".//section[starts-with(@class, 'BurgerIngredients')]//span[text()='Булки']");

    // Секция "Соусы"
    public static final By SAUCES_BUTTON = By.xpath(".//section[starts-with(@class, 'BurgerIngredients')]//span[text()='Соусы']");

    // Секция "Начинки"
    public static final By FILLINGS_BUTTON = By.xpath(".//section[starts-with(@class, 'BurgerIngredients')]//span[text()='Начинки']");

    // Выбранная в данный момент секция
    public static final By SELECTED_CONSTRUCTOR_SECTION = By.xpath(".//div[contains(@class, 'tab_tab_type_current')]");

    // Нажать кнопку "Войти в аккаунт"
    public void pressLogin() {
        driver.findElement(LOGIN_BUTTON).click();
    }

    // Нажать кнопку "Личный кабинет"
    public void pressPersonalAccount() {
        driver.findElement(PERSONAL_ACCOUNT).click();
    }

    // Нажать ссылку "Конструктор"
    public void pressConstructorLink() {
        driver.findElement(CONSTRUCTOR_LINK).click();
    }

    // Видна ли кнопка "Оформить заказ"
    public Boolean isCreateOrderButtonVisible() {
        return driver.findElement(CREATE_ORDER).isDisplayed();
    }

    // Кликнуть по лого
    public void pressLogo() {
        driver.findElement(LOGO).click();
    }

    // Кликнуть на секцию "Булки"
    public void pressBuns() {
        driver.findElement(BUNS_BUTTON).click();
    }

    // Кликнуть на секцию "Соусы"
    public void pressSauces() {
        driver.findElement(SAUCES_BUTTON).click();
    }

    // Кликнуть на секцию "Начинки"
    public void pressFillings() {
        driver.findElement(FILLINGS_BUTTON).click();
    }

    public boolean isSectionSelected(String section) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.textToBePresentInElementLocated(SELECTED_CONSTRUCTOR_SECTION, section));
    }

    // Ждем загрузки формы
    public void waitFormIsLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(BURGER_CONSTRUCTOR));
    }
}
