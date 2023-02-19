import java.util.HashMap;
import java.util.Map;

public class ExchangeRateManager implements ConverterManager {
    private final Map<String, Map<String, Double>> exchanges = new HashMap<>();

    ExchangeRateManager() {
        this.setupValues();
    }

    public void setupValues() {
        Map<String, Double> pen = new HashMap<>();
        pen.put("USD", 0.26);
        pen.put("EUR", 0.24);
        pen.put("MXN", 4.81);
        pen.put("COP", 1279.59);
        exchanges.put("PEN", pen);

        Map<String, Double> usd = new HashMap<>();
        usd.put("PEN", 3.82);
        usd.put("EUR", 0.93);
        usd.put("MXN", 18.36);
        usd.put("COP", 4882.64);
        exchanges.put("USD", usd);

        Map<String, Double> eur = new HashMap<>();
        eur.put("PEN", 4.09);
        eur.put("USD", 1.07);
        eur.put("MXN", 19.64);
        eur.put("COP", 5234.39);
        exchanges.put("EUR", eur);

        Map<String, Double> mxn = new HashMap<>();
        mxn.put("PEN", 0.21);
        mxn.put("USD", 0.054);
        mxn.put("EUR", 0.051);
        mxn.put("COP", 265.92);
        exchanges.put("MXN", mxn);

        Map<String, Double> cop = new HashMap<>();
        cop.put("PEN", 0.0039);
        cop.put("USD", 0.00020);
        cop.put("EUR", 0.00019);
        cop.put("MXN", 0.0038);
        exchanges.put("COP", cop);
    }

    public Double convert(String from, String to, int amount) {
        Map<String, Double> fromExchange = this.exchanges.get(from);

        return amount * fromExchange.get(to);
    }
}
