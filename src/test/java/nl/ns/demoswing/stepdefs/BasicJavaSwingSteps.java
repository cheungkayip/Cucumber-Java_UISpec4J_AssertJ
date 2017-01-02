package nl.ns.demoswing.stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.ns.demoswing.impl.DemoSwing;
import org.uispec4j.*;
import org.uispec4j.interception.MainClassAdapter;

import javax.swing.*;

public class BasicJavaSwingSteps extends UISpecTestCase {

    private Window window;
    private Button button, applyGaps;
    private CheckBox checkbox;
    private RadioButton radiobutton;
    private ListBox listbox;
    private ComboBox combobox1, combobox2;
    private TextBox textbox;
    private Table table;

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
    @When("^I should check JList if it is visible \"([^\"]*)\"$")
    public void i_check_the_jlist(String jlistText) throws Throwable {
        // 2. Verify Checkbox begin state
        listbox = window.getListBox(jlistText);// Retrieve JList
        assertEquals(true, listbox.isVisible());// Verify JCheckbox is visible

        checkIfTextboxIsVisible();
    }

    @Then("^I click the \"([^\"]*)\"$")
    public void i_click_the_listitem(String itemName) throws Throwable {
        System.out.println("Listbox Name: " + listbox.getName());
        listbox.select(itemName);

    }


    /*** Start your testcase with the JComboBox*/
    @When("^I should check JComboBox \"([^\"]*)\" and \"([^\"]*)\" to be visible on the page$")
    public void i_check_the_jcombobox(String hgapName, String vgapName) throws Throwable {
        // Verify Horizontal Gap
        combobox1 = window.getComboBox(hgapName);// Retrieve
        assertEquals(true, combobox1.isVisible());// Verify Horizontal is visible

        // Verify Vertical Gap
        combobox2 = window.getComboBox(vgapName);// Retrieve
        assertEquals(true, combobox2.isVisible());// Verify Vertical is visible
        checkIfTextboxIsVisible();
    }

    @Then("^I click \"([^\"]*)\" for the \"([^\"]*)\" combobox")
    public void i_click_the_jcomboboxitem(String itemName, String comboboxName) throws Throwable {
        if(comboboxName.contentEquals("horGap")) {
//            combobox1.select(itemName);
            combobox1.select(itemName);
            System.out.println("Selected Index: " +combobox1.getAwtComponent().getSelectedItem().toString());
        }else {
            combobox2.select(itemName);
            System.out.println("Selected Index: " +combobox2.getAwtComponent().getSelectedItem().toString());
        }

    }
    @And("^I click the \"([^\"]*)\" button$")
    public void i_click_the_applygaps_button(String buttonName) throws Throwable {
        applyGaps = window.getButton(buttonName);
        Mouse.click(applyGaps);
    }

    /*** Start your testcase with the JRadioButtons*/
    @When("^I should check for RadioButton if they are there \"([^\"]*)\"$")
    public void i_select_the_radiobutton(String radiobuttonText) throws Throwable {
        radiobutton = window.getRadioButton(radiobuttonText);
        assertEquals(true, radiobutton.isVisible());
        checkIfTextboxIsVisible();
    }

    @Then("^I click the radiobutton")
    public void i_click_the_radiobutton() throws Throwable {
        Mouse.click(radiobutton);// Click the button
    }

    /*** Start your testcase with the JTables*/
    @When("^I should check if JTable \"([^\"]*)\" is available$")
    public void i_check_if_jtable_is_available(String jTableText) throws Throwable {
        table = window.getTable(jTableText);
        assertEquals(true, table.isVisible());
        checkIfTextboxIsVisible();
    }

    @Then("^I click the Row \"([^\"]*)\" and Column \"([^\"]*)\"$")
    public void i_click_the_item_in_table(int row, int column) throws Throwable {
        System.out.println("Row: " + row);
        System.out.println("Column: " + column);
        table.click(row, column);
    }
}
