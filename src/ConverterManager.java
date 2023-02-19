import java.util.Map;

public interface ConverterManager {
    void setupValues();

    Double convert(String from, String to, int val);
}
