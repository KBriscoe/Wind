package wind.bluetooth;

import android.app.Activity;
import android.app.Fragment;
import android.bluetooth.BluetoothAdapter.*;
import android.bluetooth.BluetoothDevice;
import android.companion.BluetoothLeDeviceFilter;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import java.util.*;
import java.util.Set;
import android.content.BroadcastReceiver;
import android.content.*;
import android.content.Intent;
import android.util.Pair;
import android.widget.Toast;
import android.app.Activity;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanSettings;
import android.bluetooth.le.ScanFilter;
import android.bluetooth.BluetoothManager;


@RequiresApi(api = 18)
public class PairActivity extends FragmentActivity {
    private ArrayList<BluetoothDevice> mLeDevices;
    private boolean mScanning;
    private Handler mHandler;
    private BluetoothAdapter mBluetoothAdapter;
    private int REQUEST_ENABLE_BT = 1;
    private static final long SCAN_PERIOD = 10000;
    private BluetoothLeScanner mLEScanner;
    private ScanSettings settings;
    private List<ScanFilter> filters;
    private BluetoothGatt mGatt;
    private ArrayList<String> deviceListesi = new ArrayList<String>();
    private ArrayList<String> mDeviceStore = new ArrayList<String>();
    private ArrayAdapter<String> deviceAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pairing);

        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        scanLeDevice(true);
    }

    private final BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
            System.out.println("WIND Bracelet");
            final String deviceIsim = device.getName() + " " + device.getAddress();
            Toast.makeText(getApplicationContext(), deviceIsim, Toast.LENGTH_SHORT).show();
            mDeviceStore.add(deviceIsim);


            for (final String leDevice : mDeviceStore) {
                deviceListesi.add(leDevice);
            }

        }

    };

    public void scanLeDevice( final boolean enable) {
        if (enable) {
            if (mScanning) {
                return;
            }
           //Log.d("TAG", "~ Starting Scan");
            // Stops scanning after a pre-defined scan period.

            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    //Log.d("TAG", "~ Stopping Scan (timeout)");
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            //Log.d("TAG", "~ Stopping Scan");
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
    }



}