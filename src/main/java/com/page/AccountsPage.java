package com.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class AccountsPage {

    private WebDriver driver;

    private By accountSection = By.cssSelector("#content h2");

    public AccountsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getAccountsPageTitle() throws InterruptedException {
        return driver.getTitle();
    }

    public int getAccountsSectionCount() {
        return driver.findElements(accountSection).size();
    }

    public List<String> getAccountsPageSectionsList() {
        List<String> accountList = new ArrayList<>();

//        // Wait for elements to be present
//        WebDriverWait wait = new WebDriverWait(driver, 10);
//        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(accountSection));

        List<WebElement> accountHeaderList = driver.findElements(accountSection);
        for (WebElement e : accountHeaderList) {
            String text = e.getText();
            System.out.println(text);
            accountList.add(text);
        }
        return accountList;
    }
}
