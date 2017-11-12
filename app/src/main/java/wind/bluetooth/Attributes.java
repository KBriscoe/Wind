package wind.bluetooth;
import java.util.HashMap;

/**
 * This class includes a small subset of standard GATT attributes for demonstration purposes.
 */
public class Attributes {
    private static HashMap<String, String> attributes = new HashMap();
    public static String WIND = "13208DDD-0000-1000-8000-00805F9B34FB";
    public static String CLIENT_CHARACTERISTIC_CONFIG = "13208DDD-0000-1000-8000-00805F9B34FB";

    static {
        // Sample Services.
        attributes.put("13208DDD-0000-1000-8000-00805F9B34FB", "Heart Rate Service");
        attributes.put("13208DDD-0000-1000-8000-00805F9B34FB", "Device Information Service");
        // Sample Characteristics.
        attributes.put(WIND, "Heart Rate Measurement");
        attributes.put("13208DDD-0000-1000-8000-00805F9B34FB", "Manufacturer Name String");
    }

    public static String lookup(String uuid, String defaultName) {
        String name = attributes.get(uuid);
        return name == null ? defaultName : name;
    }
}