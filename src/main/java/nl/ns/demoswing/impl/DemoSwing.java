package nl.ns.demoswing.impl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DemoSwing extends JFrame {
    private static final String gapList[] = {"0", "10", "15", "20"};
    private final static int maxGap = 20;

    private GridLayout gridLayout1 = new GridLayout(0,2);
    private GridLayout gridLayout2 = new GridLayout(2,2);


    private JPanel jPanel1 = new JPanel(); // For the labels, textfields, Buttons
    private JPanel jPanel2 = new JPanel(); // for the apply gaps
//    private BoxLayout boxLayout1 = new BoxLayout(jPanel1,BoxLayout.PAGE_AXIS);
//    private BoxLayout boxLayout2 = new BoxLayout(jPanel2,BoxLayout.PAGE_AXIS);

    private JComboBox horGapComboBox;
    private JComboBox verGapComboBox;

    private JLabel label1Button = new JLabel("Button 1: ");
    private JLabel label2Button = new JLabel("Button 2: ");
    private JLabel label3Button = new JLabel("Button 3: ");
    private JLabel label4Button = new JLabel("Button 4: ");
    private JLabel label5Button = new JLabel("Button 5: ");
    private JLabel resultLabel = new JLabel("Result: ");
    private JLabel label1Checkbox = new JLabel("Checkbox 1: ");
    private JLabel label2Checkbox = new JLabel("Checkbox 2: ");
    private JLabel label3Checkbox = new JLabel("Checkbox 3: ");

    private JButton button1 = new JButton("Button1");
    private JButton button2 = new JButton("Button2");
    private JButton button3 = new JButton("Button3");
    private JButton button4 = new JButton("Long-Named Button 4");
    private JButton button5 = new JButton("Button5");
    private JButton applyButton = new JButton("Apply gaps");
    private JButton fakeButton = new JButton("Just fake button");

    private JTextField field1 = new JTextField("Result");

    private JCheckBox checkbox1 = new JCheckBox("Checkbox1");
    private JCheckBox checkbox2 = new JCheckBox("Checkbox2");
    private JCheckBox checkbox3 = new JCheckBox("Checkbox3");

    private DemoSwing(String name) {
        super(name);
        setResizable(false);
    }

    private void initGaps() {
        horGapComboBox = new JComboBox(gapList);
        verGapComboBox = new JComboBox(gapList);
    }

    private void createButtonListener(JButton buttonObject) {
        final String someText = "You have clicked " + buttonObject.getText();

        buttonObject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                field1.setText("");
                field1.setText(someText);
            }
        });

    }

    private void checkboxListener(final JCheckBox checkbox) {
        final String checkboxSelectedText = "You have selected " + checkbox.getText();
        final String checkboxUnselectedText = "You have Unselected " + checkbox.getText();
        checkbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkbox.isSelected()){
                    field1.setText("");
                    field1.setText(checkboxSelectedText);
                }else{
                    field1.setText("");
                    field1.setText(checkboxUnselectedText);
                }

            }
        });
    }

    private void applyGapsListener(JButton applyButton) {
        //Process the Apply gaps button press
        applyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                String horGap = (String)horGapComboBox.getSelectedItem();
                //Get the vertical gap value
                String verGap = (String)verGapComboBox.getSelectedItem();
                //Set up the horizontal gap value

                gridLayout1.setHgap(Integer.parseInt(horGap));
                //Set up the vertical gap value
                gridLayout1.setVgap(Integer.parseInt(verGap));
                //Set up the layout of the buttons
                gridLayout1.layoutContainer(jPanel1);
            }
        });
    }

    private void addComponentsToPane(final Container pane) {
        initGaps();

        jPanel1.setLayout(gridLayout1);
        jPanel2.setLayout(gridLayout2);

        //Set up components preferred size
        Dimension buttonSize = fakeButton.getPreferredSize();
        jPanel1.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));

        //Add labels + buttons to experiment with Grid Layout
        jPanel1.add(label1Button);
        jPanel1.add(button1);

        jPanel1.add(label2Button);
        jPanel1.add(button2);

        jPanel1.add(label3Button);
        jPanel1.add(button3);

        jPanel1.add(label4Button);
        jPanel1.add(button4);

        jPanel1.add(label5Button);
        jPanel1.add(button5);

        jPanel1.add(resultLabel);
        field1.setSize(new Dimension(2,2));
        jPanel1.add(field1);

        jPanel1.add(label1Checkbox);
        jPanel1.add(checkbox1);
        jPanel1.add(label2Checkbox);
        jPanel1.add(checkbox2);
        jPanel1.add(label3Checkbox);
        jPanel1.add(checkbox3);

        //Add controls to set up horizontal and vertical gaps
        jPanel2.add(new Label("Horizontal gap:"));
        jPanel2.add(new Label("Vertical gap:"));
        jPanel2.add(new Label(" "));
        jPanel2.add(horGapComboBox);
        jPanel2.add(verGapComboBox);
        jPanel2.add(applyButton);


        applyGapsListener(applyButton); // Do some action with Apply Gaps button

        createButtonListener(button1); // Add the Button Listeners to the application
        createButtonListener(button2);
        createButtonListener(button3);
        createButtonListener(button4);
        createButtonListener(button5);

        checkboxListener(checkbox1); // Add the checkbox Listeners to the Application
        checkboxListener(checkbox2);
        checkboxListener(checkbox3);

        pane.add(jPanel1, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(jPanel2, BorderLayout.SOUTH);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        DemoSwing frame = new DemoSwing("Demo Swing Application");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //Set up the content pane.
        frame.addComponentsToPane(frame.getContentPane());
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        /* Use an appropriate Look and Feel */
        //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        /* Turn off metal's use of bold fonts */
        UIManager.put("swing.boldMetal", Boolean.FALSE);

        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}