package nl.ns.demoswing.stepdefs.assertj;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.ns.demoswing.impl.DemoSwing;
import org.assertj.swing.annotation.GUITest;
import org.assertj.swing.core.BasicRobot;
import org.assertj.swing.core.Robot;
import org.assertj.swing.data.TableCell;
import org.assertj.swing.finder.WindowFinder;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JTableFixture;
import org.assertj.swing.junit.runner.GUITestRunner;
import org.junit.runner.RunWith;

import javax.swing.*;

import static org.testng.AssertJUnit.assertEquals;

@GUITest
@RunWith(GUITestRunner.class)
public class AssertJSteps {

    private FrameFixture frame;
    private Robot robot;

    @cucumber.api.java.Before
    public void preStepsTest() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.robot = BasicRobot.robotWithCurrentAwtHierarchy();
        DemoSwing.main(null);

        this.frame = WindowFinder.findFrame(DemoSwing.class).using(this.robot);
    }

    @After
    public void cleanup() {
        if (this.robot != null) {
            System.out.println("Cleanup");
            this.robot.cleanUp();
        }
        if (this.frame != null) {
            System.out.println("frameCleanUp");
            this.frame.cleanUp();
        }
    }

    /*** Generic Steps used by all Testcases*/
    @Given("^I should start the java swing gui")
    public void open_the_java_swing_gui() throws Throwable {

    }

    public void checkIfTextboxIsVisible() {
        frame.textBox("Result").requireVisible();
        frame.textBox("Result").text().equals("Result");
    }

    @Then("^I should see the JTextField change to \"([^\"]*)\"$")
    public void after_i_click_checkbox_jtextfield_changes(String textboxString) throws Throwable {
        frame.textBox("Result").text().equals(textboxString);
    }

    /*** Start your testcase with the buttons*/
    @When("^I should check for buttons and textfield visibility \"([^\"]*)\"$")
    public void i_check_if_the_buttons_are_visible(String buttonText) throws Throwable {
        frame.button(buttonText).requireVisible();
        checkIfTextboxIsVisible();
    }

    @Then("^I click the button \"([^\"]*)\"$")
    public void i_click_button(String buttonName) throws Throwable {
        frame.button(buttonName).click();
    }

    /*** Start your testcase with the checkboxes*/
    @When("^I should check for JCheckbox if they are there \"([^\"]*)\"$")
    public void i_check_the_checkboxes(String checkboxText) throws Throwable {
        frame.checkBox(checkboxText).requireVisible();
    }

    @Then("^I click the checkbox \"([^\"]*)\"$")
    public void i_click_the_checkbox(String checkboxText) throws Throwable {
       frame.checkBox(checkboxText).click();
    }

    /*** Start your testcase with the JList*/
    @When("^I should check JList if it is visible \"([^\"]*)\"$")
    public void i_check_the_jlist(String jlistText) throws Throwable {
        frame.list(jlistText).requireVisible();
    }

    @Then("^I click the \"([^\"]*)\"$")
    public void i_click_the_listitem(String itemName) throws Throwable {
        frame.list("List").clickItem(itemName);
    }


    /*** Start your testcase with the JComboBox*/
    @When("^I should check JComboBox \"([^\"]*)\" and \"([^\"]*)\" to be visible on the page$")
    public void i_check_the_jcombobox(String hgapName, String vgapName) throws Throwable {
        frame.comboBox(hgapName).requireVisible();
        frame.comboBox(vgapName).requireVisible();
    }

    @Then("^I click \"([^\"]*)\" for the \"([^\"]*)\" combobox")
    public void i_click_the_jcomboboxitem(String itemName, String comboboxName) throws Throwable {
        frame.comboBox(comboboxName).selectItem(itemName).click();
    }

    @And("^I click the \"([^\"]*)\" button$")
    public void i_click_the_applygaps_button(String buttonName) throws Throwable {
        frame.button(buttonName).click();
    }

    /*** Start your testcase with the JRadioButtons*/
    @When("^I should check for RadioButton if they are there \"([^\"]*)\"$")
    public void i_select_the_radiobutton(String radiobuttonText) throws Throwable {
        frame.radioButton(radiobuttonText).requireVisible();
    }

    @Then("^I click the radiobutton \"([^\"]*)\"$")
    public void i_click_the_radiobutton(String radioButtonName) throws Throwable {
        frame.radioButton(radioButtonName).click();
    }

    /*** Start your testcase with the JTables*/
    @When("^I should check if JTable \"([^\"]*)\" is available$")
    public void i_check_if_jtable_is_available(String jTableText) throws Throwable {
        frame.table(jTableText).requireVisible();
    }

    @Then("^I click the Row \"([^\"]*)\" and Column \"([^\"]*)\"$")
    public void i_click_the_item_in_table(int row, int column) throws Throwable {
        JTableFixture cell = frame.table("Table Example").selectCells(TableCell.row(row).column(column));
        cell.click();
    }

    /*** Start your testcase with the Jtree*/
    @When("^I should check if the Jtree \"([^\"]*)\" is available$")
    public void i_check_if_jtree_is_available(String jtreeName) throws Throwable {
        frame.tree(jtreeName).requireVisible();
    }

    @Then("^I click the Row \"([^\"]*)\" in the \"([^\"]*)\"$")
    public void i_click_the_item_in_table(int itemName, String jtreeName) throws Throwable {
        frame.tree(jtreeName).clickRow(itemName);
    }

    /*** Start your testcase with the AbstractButton*/
    @Then("^I should check if the AbstractButton label has the text \"([^\"]*)\"$")
    public void check_if_the_abstractbutton_is_visible(String buttonBeginStateLabel) throws Throwable {
        frame.button("AbstractButton").text().equals(buttonBeginStateLabel);
    }

    @Then("^I click the AbstractButton$")
    public void i_click_the_abstractbutton() throws Throwable {
        frame.button("AbstractButton").click();
    }

    @And("^I should see the AbstractButton label changed to \"([^\"]*)\"$")
    public void i_should_check_if_abstractLabel_has_changed(String labelName) throws Throwable {
        frame.button("AbstractButton").text().equals(labelName);
    }

    /*** Start your testcase with the Formatted Fields*/
    @When("^I should check for formatted textfield to be visible \"([^\"]*)\"$")
    public void check_formatted_textfield_is_visible(String fieldValue) throws Throwable {
        frame.textBox("FormattedTextField").requireVisible();
    }

    @Then("^I should fill in a different value \"([^\"]*)\" which is not allowed$")
    public void fill_in_bad_value(String badValue) throws Throwable {
        frame.textBox("FormattedTextField").setText(badValue);
        assertEquals("123-45-6789",frame.textBox("FormattedTextField").text());
    }

    @And("^I should fill in a good value \"([^\"]*)\" and check if this is ok$")
    public void fill_in_good_value(String goodValue) throws Throwable {
        frame.textBox("FormattedTextField").setText(goodValue);
        assertEquals("333-33-4444",frame.textBox("FormattedTextField").text());
    }
}
