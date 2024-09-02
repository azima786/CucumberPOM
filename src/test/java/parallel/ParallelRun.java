package parallel;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import org.testng.annotations.DataProvider;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/parallel/"},
        glue = {"parallel"},
        tags = "@All",
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "html:target/cucumber-reports/html",
                "json:target/cucumber-reports/cucumber.json",
                "timeline:test-output-thread/"
        })

public class ParallelRun extends AbstractTestNGCucumberTests {


    @DataProvider(parallel = true)
    public Object[][] scenario (){
        return super.scenarios();
    }



}
