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
    static Color graphColor;

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


        // convert xPoints and yPoints from ArrayList<Integer> to int[] and change the x, y coordinate value to make it match the new origin (getWidth()/2, getHeight()/2)
        int[] xpoints = new int[this.xPoints.size()];
        int[] ypoints = new int[this.yPoints.size()];
        for (int i = 0; i < this.xPoints.size(); i++) {
            xpoints[i] = getWidth() / 2 + this.xPoints.get(i);
            ypoints[i] = getHeight() / 2 - this.yPoints.get(i);
        }
        g.setColor(graphColor);
        g.drawPolyline(xpoints, ypoints, xpoints.length);
    }
}
