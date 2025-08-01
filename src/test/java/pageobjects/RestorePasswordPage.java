package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePasswordPage {
    private final WebDriver driver;

    public RestorePasswordPage(WebDriver driver){
        this.driver = driver;
    }

    // Ссылка "Войти"
    private final By LOGIN_LINK = By.xpath(".//a[contains(text(), 'Войти')]");

    // Нажать ссылку "Войти"
    public void pressLoginLink() {
        driver.findElement(LOGIN_LINK).click();
    }
}
