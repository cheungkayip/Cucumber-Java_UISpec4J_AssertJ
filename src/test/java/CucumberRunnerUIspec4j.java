import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "json:target/cucumber-reports/json", "html:target/cucumber-reports/html"},
        tags = {
                "@Buttons, " +
                "@Checkboxes, " +
                "@Comboboxes, " +
                "@Lists, " +
                "@Radiobuttons, " +
                "@Tables, " +
                "@JTree, " +
                "@AbstractButton"
        },
        glue = {"nl.ns.demoswing.stepdefs.uispec4j"})
public class CucumberRunnerUIspec4j {
}