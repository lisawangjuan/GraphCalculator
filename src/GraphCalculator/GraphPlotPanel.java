package GraphCalculator;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


// take two ArrayList<Integer> and a String as parameters, which represnts a list of x , y coordinates and equation name
// plot a graph corresponding to the given equation
public class GraphPlotPanel extends JPanel {
    private ArrayList<Integer> xPoints;
    private ArrayList<Integer> yPoints;
    private String equation;

    // constructor set backgroud color and font style
    public GraphPlotPanel() {
        setPreferredSize(new Dimension(400, 450));
        setBackground(Color.lightGray);
        setFont(new Font("Serif", Font.BOLD + Font.ITALIC, 14));
        setVisible(true);
    }

    // take coordinates and equation name parameters
    public void plot(ArrayList<Integer> xPoints, ArrayList<Integer> yPoints, String equation) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.equation = equation;
    }


    // draw the graph
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the x -axis
        g.drawLine(20, getHeight() / 2, getWidth() - 20, getHeight() / 2);
        g.drawString("X-axis", getWidth() - 40, getHeight() / 2 - 10);

        // draw the y-axis
        g.drawLine(getWidth() / 2, getHeight() - 20, getWidth() / 2, 20);
        g.drawString("Y-axis", getWidth() / 2, 20);

        // draw equation name
        g.drawString("test", 20, 20);
//        g.drawString(this.equation, 20, 20);


        // convert xPoints and yPoints from ArrayList<Integer> to int[] and change the x, y coordinate value to make it match the new origin (getWidth()/2, getHeight()/2)
        int[] xpoints = new int[this.xPoints.size()];
        int[] ypoints = new int[this.yPoints.size()];
        for (int i = 0; i < this.xPoints.size(); i++) {
            xpoints[i] = getWidth() / 2 + this.xPoints.get(i);
            ypoints[i] = getHeight() / 2 - this.yPoints.get(i);
        }
        g.drawPolyline(xpoints, ypoints, xpoints.length);
    }


    // for testing
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setSize(400, 250);
        frame.setTitle("Graph of the equation");
        frame.setLocationRelativeTo(null);//center
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int[] x = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        int[] y = {25, 16, 9, 4, 1, 0, 1, 4, 9, 16, 25};
        ArrayList<Integer> xs = new ArrayList<>();
        ArrayList<Integer> ys = new ArrayList<>();
        xs.add(-5);
        xs.add(-4);
        xs.add(-3);
        xs.add(-2);
        xs.add(-1);
        xs.add(0);
        xs.add(1);
        xs.add(2);
        xs.add(3);
        xs.add(4);
        xs.add(5);
        ys.add(25);
        ys.add(16);
        ys.add(9);
        ys.add(4);
        ys.add(1);
        ys.add(0);
        ys.add(1);
        ys.add(4);
        ys.add(9);
        ys.add(16);
        ys.add(25);

        GraphPlotPanel graph = new GraphPlotPanel();
        graph.plot(xs, ys, "y= x^2");
        frame.add(graph);
    }
}
