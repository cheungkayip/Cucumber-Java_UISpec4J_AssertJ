package nl.ns.balieapp.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import nl.ns.balieapp.impl.DemoSwing;
import org.uispec4j.*;
import org.uispec4j.interception.MainClassAdapter;

/**
 * Created by kayipcheung on 21/12/2016.
 */

public class TestSwingStepDefs extends UISpecTestCase {

    Window window;
    Button button;
    TextBox textbox;
/**
 * We then tell this class that it needs to run the DemoSwing
 application using the <code>main()</code> found in the
 <code>DemoSwing</code> class, and that it can run this
 application with no arguments. To do this, we set up an "adapter"
* */

/**
 * <p>The <code>UISpecTestCase</code> class proposes a
 <code>getMainWindow()</code> method that uses the
 <code>UISpecAdapter</code> introduced above to return an UISpec4J
 <code>Window</code> object representing the window displayed by the
 application. This <code>Window</code> object can be used to fetch
 individual UI components such as the "Button 1" button, the text fields</p>
 * */
    @Given("^I have opened the Java Swing GUI and check \"([^\"]*)\"$")
    public void i_have_opened_the_Java_Swing_GUI(String buttonText) throws Throwable {
        setAdapter(new MainClassAdapter(DemoSwing.class, new String[0]));
        // 1. Retrieve the components
        window = getMainWindow();
        // 2. Verify Button1 begin state
        button = window.getButton(buttonText);// Retrieve JButton
        assertEquals(true,button.isVisible());// Verify Button is visible
        // 3. Verify JTextField begin state
        textbox = window.getTextBox("Result");// Retrieve JTextField
        assertEquals(true,textbox.isVisible());// Verify JTextField is visible
        assertEquals("Result",textbox.getText());// Verify JTextField text is Result
    }

    @When("^I click the button")
    public void i_click_button() throws Throwable {
        // 1. Click the button1
        button.click();
    }

    @Then("^I should see the JTextField change to \"([^\"]*)\"$")
    public void i_should_see_the_JTextField_change_to(String textboxString) throws Throwable {
        assertEquals(textboxString,textbox.getText());
    }

}
