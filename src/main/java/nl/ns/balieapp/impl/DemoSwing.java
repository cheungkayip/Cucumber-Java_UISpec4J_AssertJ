package nl.ns.balieapp.impl;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DemoSwing extends JFrame {
    private static final String gapList[] = {"0", "10", "15", "20"};
    private final static int maxGap = 20;
    private JComboBox horGapComboBox;
    private JComboBox verGapComboBox;
    private JButton button1 = new JButton("Button1");
    private JButton button2 = new JButton("Button2");
    private JButton button3 = new JButton("Button3");
    private JButton button4 = new JButton("Long-Named Button 4");
    private JButton button5 = new JButton("Button5");
    private JTextField field1 = new JTextField("Result");
    private JButton applyButton = new JButton("Apply gaps");
    private GridLayout experimentLayout = new GridLayout(0,2);

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

    private void addComponentsToPane(final Container pane) {
        initGaps();
        final JPanel compsToExperiment = new JPanel();
        compsToExperiment.setLayout(experimentLayout);
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(2,3));

        //Set up components preferred size
        JButton b = new JButton("Just fake button");
        Dimension buttonSize = b.getPreferredSize();
        compsToExperiment.setPreferredSize(new Dimension((int)(buttonSize.getWidth() * 2.5)+maxGap,
                (int)(buttonSize.getHeight() * 3.5)+maxGap * 2));

        //Add buttons to experiment with Grid Layout
        compsToExperiment.add(button1);
        compsToExperiment.add(button2);
        compsToExperiment.add(button3);
        compsToExperiment.add(button4);
        compsToExperiment.add(button5);
        compsToExperiment.add(field1);

        //Add controls to set up horizontal and vertical gaps
        controls.add(new Label("Horizontal gap:"));
        controls.add(new Label("Vertical gap:"));
        controls.add(new Label(" "));
        controls.add(horGapComboBox);
        controls.add(verGapComboBox);
        controls.add(applyButton);

        //Process the Apply gaps button press
        applyButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Get the horizontal gap value
                String horGap = (String)horGapComboBox.getSelectedItem();
                //Get the vertical gap value
                String verGap = (String)verGapComboBox.getSelectedItem();
                //Set up the horizontal gap value
                experimentLayout.setHgap(Integer.parseInt(horGap));
                //Set up the vertical gap value
                experimentLayout.setVgap(Integer.parseInt(verGap));
                //Set up the layout of the buttons
                experimentLayout.layoutContainer(compsToExperiment);
            }
        });

        createButtonListener(button1);
        createButtonListener(button2);
        createButtonListener(button3);
        createButtonListener(button4);
        createButtonListener(button5);

        pane.add(compsToExperiment, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(controls, BorderLayout.SOUTH);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method is invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        DemoSwing frame = new DemoSwing("GridLayoutDemo");
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