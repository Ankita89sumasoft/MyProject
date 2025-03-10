import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.IOException;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/Feature"},
        glue = {"StepDefination"},
        tags = "@user_API_Test or  @Pet_API_Test",
        monochrome = true,
        //plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
        plugin = {"html:target/QA-Automation-html-report.html",
                "json:target/QA_Automation.json",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "rerun:target/failedrerun.txt"})

public class TestRunner {
    @AfterClass
    public static void writeExtendReport() throws IOException
    {
    }
}
