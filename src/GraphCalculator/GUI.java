package GraphCalculator;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


public class GUI extends JFrame implements ActionListener {
//    rightPanel contains all calculator and history part
    private final JPanel rightPanel;
//    calculator panel and its sub-panels
    private final JPanel calPanel, keyPanel, textPanel, calButPanel;
//    private final JPanel keyPanel;
//    private final JPanel textPanel;
//    private final JPanel calButPanel;

//    history panel and its all sub-panels
    private final JPanel historyPanel, xAxisPanel, yAxisPanel, equationPanel, hisButPanel;
//    private final JPanel equationPanel;
//    private final JPanel hisButPanel;
    private final JScrollPane hisScrollPane;

//    graph panel
//    private final JPanel graphPanel;
    private final JLabel equationLabel, xAxisLabel, yAxisLabel, equationName, colorLabel, historyLabel;
    private final JTextArea text;
    private final JTextField equationText, xAxisText, yAxisText;
    private JList historyList;
    private final JComboBox<String> colorBox;
    private final DefaultListModel model;
    private final JButton but[], butAdd, butMinus, butMultiply, butDivide,
            butEqual, butCancel, butSave, butSquareRoot,butCos, butSin, butTan, butPower, butlog, butPlot, butErase, butLoad;
    private final Calculator calc;

    private final String[] buttonValue = { "0", "1", "2", "3", "4", "5", "6",
            "7", "8", "9" };
    private static final String[] colorNames = {"Black", "Red", "Blue", "Green", "Yellow"};
    private static final Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

    public GUI() {
        this.setTitle("Calculator");
        this.setLayout(new FlowLayout());
        this.setResizable(true);

        // rightPanel initialization
        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

        // calculator part
        calPanel = new JPanel();
        calPanel.setLayout(new BoxLayout(calPanel, BoxLayout.Y_AXIS));
        textPanel = new JPanel(new FlowLayout());
        keyPanel = new JPanel(new GridLayout(0, 5));
        calButPanel = new JPanel(new FlowLayout());

        text = new JTextArea(2, 30);
        but = new JButton[10];
        for (int i = 0; i < 10; i++) {
            but[i] = new JButton(String.valueOf(i));
        }
        butAdd = new JButton("+");
        butMinus = new JButton("-");
        butMultiply = new JButton("*");
        butDivide = new JButton("/");
        butPower = new JButton("^");
        butSquareRoot = new JButton("√");
        butCos = new JButton("Cos");
        butSin = new JButton("Sin");
        butTan = new JButton("Tan");
        butlog = new JButton("log10(x)");
        butEqual = new JButton("=");
        butCancel = new JButton("C");
        butSave = new JButton("Save");

        calc = new Calculator();


        // history panel part
        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        equationPanel = new JPanel(new FlowLayout());
        xAxisPanel = new JPanel(new FlowLayout());
        yAxisPanel = new JPanel(new FlowLayout());
//        equationPanel = new JPanel(new GridLayout(3, 2));
        hisButPanel = new JPanel(new FlowLayout());

        // equation label and text field
        equationLabel = new JLabel("Equation:");
        equationName = new JLabel("Y = f(x) =  separate by space");
        equationName.setPreferredSize(new Dimension(110, 20));
        equationText = new JTextField(40);
        equationText.setText("Enter f(x) here");

        // x axis range part
        xAxisLabel = new JLabel("X Axis Range: ");
        xAxisLabel.setPreferredSize(new Dimension(110, 20));
        xAxisText = new JTextField(40);
        xAxisText.setText("Enter range in the form of [-10,10]");

        // y axis range part
        yAxisLabel = new JLabel("Y Axis Range: :");
        yAxisLabel.setPreferredSize(new Dimension(110, 20));
        yAxisText = new JTextField(40);
        yAxisText.setText("Enter range in the form of [-10,10]");

        colorLabel = new JLabel("Select Color:");
        colorBox = new JComboBox<String>(colorNames);

        historyLabel = new JLabel("History:");
        model = new DefaultListModel();
        historyList = new JList(model);
        hisScrollPane = new JScrollPane(historyList);
//        historyList.setSize(300, 80);
//        historyList.setPreferredSize(new Dimension(400, 40));
//        historyList.setFixedCellWidth(330);

        butPlot = new JButton("PLOT");
        butErase = new JButton("ERASE");
        butLoad = new JButton("LOAD");
    }

    public void init() {
        this.setVisible(true);
        this.setSize(330, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.add(calPanel);
//        this.add(historyPanel);
        this.add(rightPanel);

        rightPanel.add(historyPanel);
        rightPanel.add(Box.createRigidArea(new Dimension(0,20)));       // add space between historyPanel and calPanel
        rightPanel.add(calPanel);

        // initiate calPanel part and register related event handlers
        calPanel.add(textPanel);
        calPanel.add(keyPanel);
        calPanel.add(calButPanel);
        textPanel.add(text);

        for (int i = 0; i < 10; i++) {
            keyPanel.add(but[i]);
            but[i].addActionListener(this);
        }

        keyPanel.add(butAdd);
        keyPanel.add(butMinus);
        keyPanel.add(butMultiply);
        keyPanel.add(butDivide);
        keyPanel.add(butSquareRoot);
        keyPanel.add(butCos);
        keyPanel.add(butSin);
        keyPanel.add(butTan);
        keyPanel.add(butPower);
        keyPanel.add(butlog);

        calButPanel.add(butEqual);
        calButPanel.add(butCancel);
        calButPanel.add(butSave);

        butAdd.addActionListener(this);
        butMinus.addActionListener(this);
        butMultiply.addActionListener(this);
        butDivide.addActionListener(this);
        butSquareRoot.addActionListener(this);
        butCos.addActionListener(this);
        butSin.addActionListener(this);
        butTan.addActionListener(this);
        butPower.addActionListener(this);
        butlog.addActionListener(this);
        butEqual.addActionListener(this);
        butCancel.addActionListener(this);


        // history panel part initialization and registration

        historyList.setVisible(true);
        historyList.setVisibleRowCount(5);
        historyList.setFixedCellWidth(200);
        historyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        equationText.setSize(200, 30);
        equationPanel.add(equationName);
        equationPanel.add(equationText);
        xAxisPanel.add(xAxisLabel);
        xAxisPanel.add(xAxisText);
        yAxisPanel.add(yAxisLabel);
        yAxisPanel.add(yAxisText);
        hisButPanel.add(butPlot);
        hisButPanel.add(butErase);
        historyPanel.add(equationLabel);
        historyPanel.add(equationPanel);
        historyPanel.add(xAxisPanel);
        historyPanel.add(yAxisPanel);
        historyPanel.add(hisButPanel);
        historyPanel.add(colorLabel);
        historyPanel.add(Box.createRigidArea(new Dimension(0,5)));      // add space between components
        historyPanel.add(colorBox);
        historyPanel.add(Box.createRigidArea(new Dimension(0,5)));
        historyPanel.add(historyLabel);
        historyPanel.add(Box.createRigidArea(new Dimension(0,5)));
//        historyPanel.add(historyList);
        historyPanel.add(hisScrollPane);
        historyPanel.add(Box.createRigidArea(new Dimension(0,5)));
        historyPanel.add(butLoad);
        historyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
//        calPanel.add(equationLabel);
//        calPanel.add(equationPanel);
//        calPanel.add(hisButPanel);
//        calPanel.add(colorLabel);
//        calPanel.add(colorBox);
//        calPanel.add(historyLabel);
//        calPanel.add(historyList);
//        calPanel.add(butLoad);
//        calPanel.setAlignmentX(Component.CENTER_ALIGNMENT);





        // register event handlers
        colorBox.addItemListener(new GUI.colorBoxHandler());
        butPlot.addActionListener(new GUI.addButtonHanlder());
        butErase.addActionListener(new GUI.delButtonHanlder());
        butLoad.addActionListener(new GUI.loadButtonHanlder());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        final Object source = e.getSource();

        for (int i = 0; i < 10; i++) {
            if (source == but[i]) {
                text.replaceSelection(buttonValue[i]);
                return;
            }
        }

        if (source == butAdd) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.add, reader()));
        }

        if (source == butMinus) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.minus, reader()));
        }

        if (source == butMultiply) {
            writer(calc.calculateBi(Calculator.BiOperatorModes.multiply,
                    reader()));
        }

        if (source == butDivide) {
            writer(calc
                    .calculateBi(Calculator.BiOperatorModes.divide, reader()));
        }
        if (source == butPower) {
            writer(calc
                    .calculateBi(Calculator.BiOperatorModes.xpowerofy, reader()));
        }

        if (source == butSquareRoot) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.squareRoot,
                    reader()));
        }

        if (source == butCos) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.cos,
                    reader()));
        }

        if (source == butSin) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.sin,
                    reader()));
        }

        if (source == butTan) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.tan,
                    reader()));
        }
        if (source == butlog) {
            writer(calc.calculateMono(Calculator.MonoOperatorModes.log,
                    reader()));
        }

        if (source == butEqual) {
            writer(calc.calculateEqual(reader()));
        }

        if (source == butCancel) {
            writer(calc.reset());
        }

        text.selectAll();
    }

    public Double reader() {
        Double num;
        String str;
        str = text.getText();
        num = Double.valueOf(str);

        return num;
    }

    public void writer(final Double num) {
        if (Double.isNaN(num)) {
            text.setText("");
        } else {
            text.setText(Double.toString(num));
        }
    }


    //    implement color combobox hanlder, when a specific color is selected, the equation text changes its color
    private class colorBoxHandler implements ItemListener {
        //        handle color combobox event
        @Override
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                equationText.setForeground(colors[colorBox.getSelectedIndex()]);
            }
        }
    }

    //    implement add button hanlder, which addes equation to history field
    private class addButtonHanlder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            String equation = "y = " + equationText.getText();
            System.out.println("y = " + equation);
            model.addElement(equation);
            historyList.setModel(model);
        }
    }

    //    implement del button, remove selected equation from history list
    private class delButtonHanlder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (historyList.getSelectedIndex() != -1) {
                int i = historyList.getSelectedIndex();
                model.remove(i);
                historyList.setModel(model);
            }
        }
    }

    //    implement del button, remove selected equation from history list
    private class loadButtonHanlder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            if (historyList.getSelectedIndex() != -1) {
                int i = historyList.getSelectedIndex();
                String equation = (String) model.getElementAt(i);
                equationText.setText(equation.substring(4));
            }
        }
    }
}
