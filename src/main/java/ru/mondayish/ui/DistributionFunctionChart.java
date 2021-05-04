package ru.mondayish.ui;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.xy.XYDataset;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.stream.IntStream;

public class DistributionFunctionChart extends ApplicationFrame {

    public DistributionFunctionChart(String title, XYDataset dataset) {
        super(title);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(700, 480));
        setContentPane(chartPanel);
    }

    private JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart("Функция распределения", "X", "F(x)",
                dataset, PlotOrientation.VERTICAL, true, false, false);

        chart.setBackgroundPaint(Color.white);
        XYPlot plot = chart.getXYPlot();
        plot.setBackgroundPaint(new Color(232, 232, 232));
        plot.setAxisOffset(new RectangleInsets(1.0, 1.0, 1.0, 1.0));

        ValueAxis axis = plot.getDomainAxis();
        axis.setAxisLineVisible(false);

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        IntStream.range(0, dataset.getSeriesCount()).forEach(i -> {
            renderer.setSeriesPaint(i, Color.red);
            renderer.setSeriesStroke(i, new BasicStroke(2.5f));
            renderer.setSeriesShape(i, new Ellipse2D.Double(-2.5, -2.5, 5, 5));
        });
        plot.setRenderer(renderer);

        return chart;
    }
}
