package parallel;

import com.page.AccountsPage;
import com.page.LoginPage;
import com.qa.factory.DriverFactory;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class AccountsPageStepsDefinitions {

    private LoginPage loginPage = new LoginPage(DriverFactory.getDriver());
    private AccountsPage accountsPage;

    @Given("User has already logged into application")
    public void user_has_already_logged_into_application(DataTable credentialTable) {
        List<Map<String, String>> credList = credentialTable.asMaps();
        String userName = credList.get(0).get("username");
        String password = credList.get(0).get("password");

        DriverFactory.getDriver().get("https://naveenautomationlabs.com/opencart/index.php?route=account/login");
        accountsPage = loginPage.doLogin(userName, password);

    }

    @Given("User is on Accounts Page")
    public void user_is_on_accounts_page() throws InterruptedException {

       String title =  accountsPage.getAccountsPageTitle();
        System.out.println("Accounts page title " + title);
    }

    @Then("user gets Accounts section")
    public void user_gets_accounts_section(DataTable sectionTable) {
        List<String> expectedAccountsSectionList = sectionTable.asList();
        System.out.println("Expected accounts List " + expectedAccountsSectionList);
        List<String> actualAccountSection= accountsPage.getAccountsPageSectionsList();
        System.out.println("Actual list " + actualAccountSection);

        Assert.assertTrue(expectedAccountsSectionList.containsAll(actualAccountSection));

    }

    @Then("accounts section count should be {int}")
    public void accounts_section_count_should_be(Integer expectedSectionCount) {
        System.out.println("Accounts count is: " + accountsPage.getAccountsSectionCount());
        Assert.assertTrue(accountsPage.getAccountsSectionCount() == 4);
    }

}
