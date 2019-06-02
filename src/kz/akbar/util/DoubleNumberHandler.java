package kz.akbar.util;

import java.util.List;

public class DoubleNumberHandler {

    public double sumOfDoubles(List<Double> doubles) {
        return doubles.stream().mapToDouble(Double::doubleValue).sum();
    }

    public double averageOfDoubles(List<Double> doubles){
        return doubles.stream().mapToDouble(Double::doubleValue).average().getAsDouble();
    }
}
