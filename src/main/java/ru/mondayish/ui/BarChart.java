package ru.mondayish.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.IntervalXYDataset;

import java.awt.*;

public class BarChart extends ApplicationFrame {

    public BarChart(String title, IntervalXYDataset dataset) {
        super(title);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 480));
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(IntervalXYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYBarChart("Гистограмма частот", "X",
                false, "P", dataset);

        chart.setBackgroundPaint(Color.white);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(232, 232, 232));
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));

        return chart;
    }
}
