package nl.ns.demoswing.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.ns.demoswing.impl.DemoSwing;
import org.uispec4j.*;
import org.uispec4j.interception.MainClassAdapter;

public class BasicJavaSwingSteps extends UISpecTestCase {

    private Window window;
    private Button button;
    private CheckBox checkbox;
    private ListBox listbox;
    private TextBox textbox;

    /*** Generic Steps used by all Testcases*/
    @Given("^I should start the java swing gui")
    public void open_the_java_swing_gui() throws Throwable {
        setAdapter(new MainClassAdapter(DemoSwing.class));
        window = getMainWindow();// Retrieve the components
    }

    private void checkIfTextboxIsVisible() {
        // 3. Verify JTextField begin state
        textbox = window.getTextBox("Result"); // Retrieve JTextField
        assertEquals(true, textbox.isVisible()); // Verify JTextField is visible
        assertEquals("Result", textbox.getText()); // Verify JTextField text is Result
    }

    @Then("^I should see the JTextField change to \"([^\"]*)\"$")
    public void after_i_click_checkbox_jtextfield_changes(String textboxString) throws Throwable {
        assertEquals(textboxString, textbox.getText());
    }

    /*** Start your testcase with the buttons*/
    @When("^I should check for buttons and textfield visibility \"([^\"]*)\"$")
    public void i_check_if_the_buttons_are_visible(String buttonText) throws Throwable {
            // 2. Verify Button begin state
            button = window.getButton(buttonText);// Retrieve JButton
            assertEquals(true, button.isVisible());// Verify Button is visible
            checkIfTextboxIsVisible();

    }

    @Then("^I click the button")
    public void i_click_button() throws Throwable {
        Mouse.click(button);// Click the button
    }

    /*** Start your testcase with the checkboxes*/
    @When("^I should check for JCheckbox if they are there \"([^\"]*)\"$")
    public void i_check_the_checkboxes(String checkboxText) throws Throwable {
        checkbox = window.getCheckBox(checkboxText);// 2. Verify Checkbox begin state
        assertEquals(true, checkbox.isVisible());// Verify JCheckbox is visible
        checkIfTextboxIsVisible();
    }

    @Then("^I click the checkbox")
    public void i_click_the_checkbox() throws Throwable {
        Mouse.click(checkbox);// Click the button
    }

    /*** Start your testcase with the JList*/
    @When("^I should check JList1 if it is visible \"([^\"]*)\"$")
    public void i_check_the_jlist(String jlistText) throws Throwable {
        // 2. Verify Checkbox begin state
        listbox = window.getListBox(jlistText);// Retrieve JList
        assertEquals(true, listbox.isVisible());// Verify JCheckbox is visible
        checkIfTextboxIsVisible();
    }

    @Then("^I click the listitem (\\d+)$")
    public void i_click_the_listitem(int itemClicked) throws Throwable {
        window.getListBox("list1").click(itemClicked);

    }

}
