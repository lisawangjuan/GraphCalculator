package GraphCalculator;

import org.omg.PortableInterceptor.INACTIVE;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

import javax.swing.*;


public class GUI extends JFrame implements ActionListener {
//    rightPanel contains all calculator and HistoryPanel part
    private final JPanel leftPanel, rightPanel;
//    calculator panel and its sub-panels
    private final JPanel calPanel, keyPanel, textPanel, calButPanel;
//    HistoryPanel panel and its all sub-panels
    private final JPanel historyPanel, xAxisPanel, yAxisPanel, equationPanel, hisButPanel;
    private final JScrollPane hisScrollPane;

    private final GraphPlotPanel graph;

//    graph panel
//    private final JPanel graphPanel;
    private final JLabel equationLabel, xAxisLabel, yAxisLabel, equationName, colorLabel, historyLabel;
    private final JTextArea text;
    private final JTextField equationText, xAxisText, yAxisText;
    private JList historyList;
    private final JComboBox<String> colorBox;
    private final DefaultListModel model;
    private final JButton but[], butAdd, butMinus, butMultiply, butDivide,
            butEqual, butCancel, butSave, butSquareRoot,butCos, butSin, butTan, butPI, butPower, butlog, butPlot, butErase, butLoad;
    private final Calculation calc;
    private Parse parse;
    private String answer = new String();

    private final String[] buttonValue = { "0", "1", "2", "3", "4", "5", "6",
            "7", "8", "9" };
    private static final String[] colorNames = {"Black", "Red", "Blue", "Green", "Yellow"};
    private static final Color[] colors = {Color.BLACK, Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};

    public GUI() {
        this.setTitle("Calculation");
        this.setLayout(new FlowLayout());
        this.setResizable(true);

        //left graph panel initialization
        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        graph = new GraphPlotPanel();

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
        butPI = new JButton("PI");
        butEqual = new JButton("=");
        butCancel = new JButton("C");
        butSave = new JButton("Save");

        calc = new Calculation();
        parse = new Parse();


        // HistoryPanel panel part
        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        equationPanel = new JPanel(new FlowLayout());
        xAxisPanel = new JPanel(new FlowLayout());
        yAxisPanel = new JPanel(new FlowLayout());
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

        butPlot = new JButton("PLOT");
        butErase = new JButton("ERASE");
        butLoad = new JButton("LOAD");
    }

    public void init() {
        this.setVisible(true);
        this.setSize(330, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(calPanel);
        this.add(historyPanel);
        Integer[] x = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        Integer[] y = {25, 16, 9, 4, 1, 0, 1, 4, 9, 16, 25};
        ArrayList<Integer> xs = new ArrayList<Integer>(Arrays.asList(x));
        ArrayList<Integer> ys = new ArrayList<Integer>(Arrays.asList(y));
        graph.plot(xs, ys, "test");
        this.add(leftPanel);
        this.add(rightPanel);

        leftPanel.add(graph);
        leftPanel.setPreferredSize(new Dimension(400,500));

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

        calButPanel.add(butPI);
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
        butPI.addActionListener(this);
        butlog.addActionListener(this);
        butEqual.addActionListener(this);
        butCancel.addActionListener(this);
        butSave.addActionListener(this);


        // HistoryPanel panel part initialization and registration
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
        historyPanel.add(hisScrollPane);
        historyPanel.add(Box.createRigidArea(new Dimension(0,5)));
        historyPanel.add(butLoad);
        historyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // register event handlers
        colorBox.addItemListener(new GUI.colorBoxHandler());
        butPlot.addActionListener(new GUI.plotButtonHanlder());
        butErase.addActionListener(new GUI.eraseButtonHanlder());
        butLoad.addActionListener(new GUI.loadButtonHanlder());

        pack();
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
            writer(calc.calculateBi(Calculation.BiOperatorModes.add, reader()));
        }

        if (source == butMinus) {
            writer(calc.calculateBi(Calculation.BiOperatorModes.minus, reader()));
        }

        if (source == butMultiply) {
            writer(calc.calculateBi(Calculation.BiOperatorModes.multiply,
                    reader()));
        }

        if (source == butDivide) {
            writer(calc.calculateBi(Calculation.BiOperatorModes.divide, reader()));
        }

        if (source == butPower) {
            writer(calc
                    .calculateBi(Calculation.BiOperatorModes.xpowerofy, reader()));
        }

        if (source == butSquareRoot) {
            writer(calc.calculateMono(Calculation.MonoOperatorModes.squareRoot,
                    reader()));
        }

        if (source == butCos) {
            writer(calc.calculateMono(Calculation.MonoOperatorModes.cos,
                    reader()));
        }

        if (source == butSin) {
            writer(calc.calculateMono(Calculation.MonoOperatorModes.sin,
                    reader()));
        }

        if (source == butTan) {
            writer(calc.calculateMono(Calculation.MonoOperatorModes.tan, reader()));
        }

        if (source == butlog) {
            writer(calc.calculateMono(Calculation.MonoOperatorModes.log,
                    reader()));
        }

        if (source == butPI) {
            text.replaceSelection("PI");
            return;
        }

        if (source == butEqual) {
            writer(calc.calculateEqual(reader()));
        }

        if (source == butCancel) {
            writer(calc.reset());
        }

        if (source == butSave) {
            if (text.getText().equals("") || text.getText().equals("Infinity")) {
                text.setText(answer);
            }
            else {
                answer = text.getText();
            }
        }
        text.selectAll();
    }

    public Double reader() {
        Double num;
        String str;
        str = text.getText();
        if (str.equals("PI")) {
            num = Math.PI;
        }
        else {
            num = Double.valueOf(str);
        }
        return num;
    }

    public void writer(final Double num) {
        if (Double.isNaN(num)) {
            text.setText("");
        }
        else {
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
                GraphPlotPanel.graphColor = colors[colorBox.getSelectedIndex()];
            }
            leftPanel.repaint();
            leftPanel.revalidate();
        }
    }

    //    implement add button hanlder, which adds equation to HistoryPanel field and plot graph on left panel
    private class plotButtonHanlder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // add current equation to history field
            String equation = "y = " + equationText.getText();
            System.out.println("y = " + equation);

//            // call draw() to plot graph in left panel
            try {
                draw();
                leftPanel.repaint();
                leftPanel.revalidate();
                model.addElement(equation);
                historyList.setModel(model);
            }
            catch (RuntimeException e) {
                JOptionPane.showMessageDialog(rightPanel, "Invalid equation, please reenter the equation, separate with space!");
            }
        }
    }

    //    implement del button, remove selected equation from HistoryPanel list and erase graph from left panel
    private class eraseButtonHanlder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // remove selected equation from HistoryPanel list
            if (historyList.getSelectedIndex() != -1) {
                int i = historyList.getSelectedIndex();
                model.remove(i);
                historyList.setModel(model);
            }

            // call erase() to erase graph from left panel
            erase();
            leftPanel.repaint();
            leftPanel.revalidate();
        }
    }

    //    implement del button, load selected equation from HistoryPanel list to equation textfield and plot graph on left panel
    private class loadButtonHanlder implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
            // load selected equation from HistoryPanel list back to equationText
            if (historyList.getSelectedIndex() != -1) {
                int i = historyList.getSelectedIndex();
                String equation = (String) model.getElementAt(i);
                equationText.setText(equation.substring(4));
            }

            // plot the graph
            draw();
            leftPanel.repaint();
            leftPanel.revalidate();
        }
    }


    // take equation field, x-axis and y-axis fields content and draw a gragh
    public void draw() {
        String equation = equationText.getText();
        String xRange = xAxisText.getText();
        String yRange = yAxisText.getText();
        ArrayList<Integer> xPoints = new ArrayList<>();
        ArrayList<Integer> yPoints = new ArrayList<>();

        // get start and end point of x, y ranges
        String[] xRanges = xRange.substring(1,xRange.length()-1).split(",");
        Integer xStart = Integer.parseInt(xRanges[0]);
        Integer xEnd = Integer.parseInt(xRanges[xRanges.length-1]);
        String[] yRanges = yRange.substring(1,yRange.length()-1).split(",");
        Integer yStart = Integer.parseInt(yRanges[0]);
        Integer yEnd = Integer.parseInt(yRanges[yRanges.length-1]);

        // get a list of x, y coordinates calculated by given equation

        for (int i = xStart; i <= xEnd; i++) {
            int y = (int) parse.calculate(equation, i);
            // truncate by using y range
            if (y >= yStart && y <= yEnd) {
                xPoints.add(i);
                yPoints.add(y);
            }
        }

        // plot to left graph panel
        graph.plot(xPoints, yPoints, equation);
    }


    // erase graph from left panel
    public void erase() {
        String equation = new String("No Equation");
        ArrayList<Integer> xPoints = new ArrayList<>();
        ArrayList<Integer> yPoints = new ArrayList<>();
        graph.plot(xPoints, yPoints, equation);
//        graph.revalidate();
    }

}
