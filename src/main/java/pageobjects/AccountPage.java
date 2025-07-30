package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AccountPage {
    private final WebDriver driver;

    public AccountPage(WebDriver driver) { this.driver = driver; }

    // Кнопка "Сохранить"
    private final By SAVE_BUTTON = By.xpath(".//button[contains(text(), 'Сохранить')]");

    // Кнопка "Выход"
    private final By LOGOUT = By.xpath(".//button[contains(text(), 'Выход')]");

    // Нажать кнопку "Выход"
    public void pressLogout() {
        driver.findElement(LOGOUT).click();
    }

    public void waitFormIsLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(SAVE_BUTTON));
    }
}
