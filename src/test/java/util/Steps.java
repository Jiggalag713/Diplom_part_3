package util;

import com.google.gson.Gson;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegisterPage;

public class Steps {
    private final ApiHelper API;
    private static final Gson GSON = new Gson();
    private WebDriver WEBDRIVER;

    public Steps(ApiHelper api, WebDriver driver) {
        this.API = api;
        this.WEBDRIVER = driver;
    }

    public void setDriver(WebDriver driver) {
        this.WEBDRIVER = driver;
    }

    @Step("Register new user")
    public void registerUser(String email, String password, String name) {
        MainPage mainPage = new MainPage(WEBDRIVER);
        mainPage.waitFormIsLoad();
        mainPage.pressLogin();
        LoginPage loginPage = new LoginPage(WEBDRIVER);
        loginPage.pressRegister();
        RegisterPage registerPage = new RegisterPage(WEBDRIVER);
        registerPage.inputName(name);
        registerPage.inputEmail(email);
        registerPage.inputPassword(password);
        registerPage.pressRegisterButton();
    }

    @Step("Create user")
    public void createUser(String email, String password, String name) {
        User user = new User(email, password, name);
        String userJson = GSON.toJson(user);
        API.sendRequestCreateUser(userJson);
    }

    @Step("Checking password correctness")
    public Boolean passwordIsIncorrect() {
        RegisterPage registerPage = new RegisterPage(WEBDRIVER);
        return registerPage.passwordIsIncorrect();
    }

    @Step("Login user")
    public void loginUser(String email, String password) {
        LoginPage loginPage = new LoginPage(WEBDRIVER);
        loginPage.waitFormIsLoad();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.pressLoginButton();
    }

    @Step("Delete existed user")
    public void deleteUser(String email, String password, String name) {
        User user = new User(email, password, name);
        String userJson = GSON.toJson(user);
        API.sendRequestDeleteUser(API.sendRequestLoginUser(userJson).path("accessToken"));
    }
}
