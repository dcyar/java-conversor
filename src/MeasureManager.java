import java.util.HashMap;
import java.util.Map;

public class MeasureManager implements ConverterManager {
    private final Map<String, Map<String, Double>> measures = new HashMap<>();

    MeasureManager() {
        this.setupValues();
    }

    public void setupValues() {
        Map<String, Double> cm = new HashMap<>();
        cm.put("M", 0.01);
        cm.put("KM", 0.00001);
        measures.put("CM", cm);

        Map<String, Double> m = new HashMap<>();
        m.put("CM", 100.0);
        m.put("KM", 0.001);
        measures.put("M", m);

        Map<String, Double> km = new HashMap<>();
        km.put("CM", 100000.0);
        km.put("M", 1000.0);
        measures.put("KM", km);
    }

    public Double convert(String from, String to, int measure) {
        Map<String, Double> fromExchange = this.measures.get(from);

        return measure * fromExchange.get(to);
    }
}
