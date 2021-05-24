package ru.mondayish.math;

import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.mondayish.models.Selection;
import ru.mondayish.models.SelectionCharacteristics;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Dataset {

    public static XYDataset createPolygonDataset(Selection selection, SelectionCharacteristics characteristics) {
        double h = round(characteristics.getRange() / getIntervalNumber(selection.getElements().size()));

        XYSeries series = new XYSeries("Bar");
        double start = characteristics.getMin(), finish = round(start + h);
        int count = 0;
        for (Double x : characteristics.getVariationRange()) {
            if (x > finish) {
                series.add(round(start + h/2), count);
                count = 0;
                start = finish;
                finish = series.getItemCount() == getIntervalNumber(selection.getElements().size()) - 1 ? characteristics.getMax() : round(start + h);
            }
            count++;
        }
        series.add(round(start + h / 2), count);

        return new XYSeriesCollection(series);

    }

    public static IntervalXYDataset createBarDataset(Selection selection, SelectionCharacteristics characteristics) {
        double h = round(characteristics.getRange() / getIntervalNumber(selection.getElements().size()));

        XYSeries series = new XYSeries("Bar");
        double start = characteristics.getMin(), finish = round(start + h);
        int count = 0;
        for (Double x : characteristics.getVariationRange()) {
            if (x > finish) {
                series.add(start + h / 2, ((double) count) / selection.getElements().size() / getIntervalNumber(selection.getElements().size()));
                count = 0;
                start = finish;
                finish = series.getItemCount() == getIntervalNumber(selection.getElements().size()) - 1 ? characteristics.getMax() : round(start + h);
            }
            count++;
        }
        series.add(start + h / 2, ((double) count) / selection.getElements().size() / getIntervalNumber(selection.getElements().size()));

        return new XYSeriesCollection(series);
    }

    public static XYDataset createDistributionFunctionDataset(Selection selection, SelectionCharacteristics characteristics) {
        double h = round(characteristics.getRange() / getIntervalNumber(selection.getElements().size()));

        Map<Double, Integer> counts = new HashMap<>();
        double start = characteristics.getMin(), finish = round(start + h);
        int count = 0;
        counts.put(start, 0);
        for (Double x : characteristics.getVariationRange()) {
            if (x > finish) {
                counts.put(finish, count);
                count = 0;
                start = finish;
                finish = counts.size() == getIntervalNumber(selection.getElements().size()) ? characteristics.getMax() : round(start + h);
            }
            count++;
        }
        counts.put(characteristics.getMax(), count);

        XYSeriesCollection dataset = new XYSeriesCollection();
        List<Double> elements = counts.keySet().stream().sorted().collect(Collectors.toList());

        double p = 0;
        start = elements.get(0);
        for (int i = 1; i < elements.size(); i++) {
            XYSeries series = new XYSeries(i);
            p += ((double) counts.get(elements.get(i))) / selection.getElements().size();
            series.add(start, p);
            start = elements.get(i);
            series.add(start, p);
            dataset.addSeries(series);
        }

        return dataset;
    }

    private static double round(double number) {
        return Math.round(number * 100) / 100.0;
    }

    private static double getIntervalNumber(int n){
        return Math.ceil(Math.log10(n)/Math.log10(2)) + 1;
    }
}
