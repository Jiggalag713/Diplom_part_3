package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Header {
    private final WebDriver driver;
    private final Actions actions;

    public Header(WebDriver driver, Actions actions){
        this.driver = driver;
        this.actions = actions;
    }
}
