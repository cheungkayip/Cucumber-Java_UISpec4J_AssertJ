import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber-reports/json", "html:target/cucumber-reports/html"},
        tags = {"@Smoke"},
        glue = {"nl.ns.demoswing.stepdefs"})
public class CucumberRunner {
}