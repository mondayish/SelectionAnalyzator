package ru.mondayish.math;

import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import ru.mondayish.models.Selection;

import java.util.HashMap;
import java.util.Map;

public class Dataset {

    public static IntervalXYDataset createDataset(Selection selection) {
        Map<Double, Integer> counts = new HashMap<>();
        selection.getElements().forEach(x -> counts.merge(x, 1, Integer::sum));
        XYSeries series = new XYSeries("Polygon");
        counts.forEach((key, value) -> series.add(key.doubleValue(), value.doubleValue() / selection.getElements().size()));
        return new XYSeriesCollection(series);
    }
}
