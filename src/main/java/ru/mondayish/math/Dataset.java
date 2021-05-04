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

    public static XYDataset createPolygonDataset(Selection selection) {
        Map<Double, Integer> counts = new HashMap<>();
        selection.getElements().forEach(x -> counts.merge(x, 1, Integer::sum));
        XYSeries series = new XYSeries("Polygon");
        counts.forEach((key, value) -> series.add(key.doubleValue(), value.doubleValue() / selection.getElements().size()));
        return new XYSeriesCollection(series);
    }

    public static IntervalXYDataset createBarDataset(Selection selection, SelectionCharacteristics characteristics) {
        long h = Math.round(characteristics.getRange() / (1 + Math.log10(selection.getElements().size()) / Math.log10(2)));
        double xStart = characteristics.getMin() - h / 2.0;
        long intervalCount = Math.round(1 + Math.log10(selection.getElements().size()) / Math.log10(2));

        XYSeries series = new XYSeries("Bar");
        double start = xStart, finish = xStart + h;
        int count = 0;
        for (Double x : characteristics.getVariationRange()) {
            if (x >= finish) {
                series.add(start + (finish - start) / 2, ((double) count) / selection.getElements().size() / intervalCount);
                count = 0;
                start = finish;
                finish = start + h;
            }
            count++;
        }

        return new XYSeriesCollection(series);
    }

    public static XYDataset createDistributionFunctionDataset(Selection selection, SelectionCharacteristics characteristics) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        Map<Double, Integer> counts = new HashMap<>();
        selection.getElements().forEach(x -> counts.merge(x, 1, Integer::sum));
        List<Double> elements = counts.keySet().stream().sorted().collect(Collectors.toList());

        double p = 0, start = elements.get(0);
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
}
