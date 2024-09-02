package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    //1. By Locators
    private By emailId = By.id("input-email");
    private By password = By.id("input-password");
    private By signInButton = By.xpath("//input[@type='submit']");
    private By forgotPwdLink = By.linkText("Forgotten Password");

    //2. Constructor of page class
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //3. Page actions: features of the page the form of methods
    public String getLoginPageTitle() {
        return driver.getTitle();
    }

    public boolean isForgotPwdLinkExist() {
        return driver.findElement(forgotPwdLink).isDisplayed();
    }

    public void enterUserName(String username) {
        driver.findElement(emailId).sendKeys(username);
    }

    public void enterPassword(String pwd) {
        driver.findElement(password).sendKeys(pwd);
    }

    public void clickOnLogin() {
        driver.findElement(signInButton).click();
    }

    public AccountsPage doLogin(String un, String pwd) {
        System.out.println("Login with: " + un + " and " + pwd);

        driver.findElement(emailId).sendKeys(un);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(signInButton).click();

        return new AccountsPage(driver);
    }
}
