package wind.bluetooth;

        import android.os.ParcelUuid;

public class Constants {

    /**
     * UUID identified with this app - set as Service UUID for BLE Advertisements.
     *
     * Bluetooth requires a certain format for UUIDs associated with Services.
     * The official specification can be found here:
     * {@link https://www.bluetooth.org/en-us/specification/assigned-numbers/service-discovery}
     */
    public static final ParcelUuid Service_UUID = ParcelUuid
            .fromString("13208DDD-0000-1000-8000-00805F9B34FB");

    public static final int REQUEST_ENABLE_BT = 1;
    public static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
}