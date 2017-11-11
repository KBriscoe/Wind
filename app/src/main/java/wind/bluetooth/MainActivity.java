package wind.bluetooth;

import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.*;
import android.view.View;

public class MainActivity extends FragmentActivity {
    //final String PREFS_NAME = "MyPrefsFile";
    //SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

    private BluetoothAdapter mBluetoothAdapter;
    private Button pairingButton;
    //Would prefer not to use a @RequiresApi
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Checking Bluetooth Settings
        if (savedInstanceState == null) {
            //The commented statement is supposed to be for lower androids, newer ones use the current statement
            //mBluetoothAdapter = mBluetoothAdapter.getDefaultAdapter();
            mBluetoothAdapter = ((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE))
                    .getAdapter();
            // Is Bluetooth supported on the device?
            if (mBluetoothAdapter != null) {
                // Check if Bluetooth is on
                if (mBluetoothAdapter.isEnabled()) {
                    // Everything is supported and enabled, load the fragments.
                    setupFragments();
                } else {

                    // Prompt user to turn on Bluetooth (logic continues in onActivityResult()).
                    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enableBtIntent, Constants.REQUEST_ENABLE_BT);
                }
            } else {
                // Bluetooth is not supported.
                showErrorText(R.string.bt_not_supported);
            }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case Constants.REQUEST_ENABLE_BT:

                if (resultCode == RESULT_OK) {

                    // Everything is supported and enabled, load the fragments.
                    setupFragments();

                } else {

                    // User declined to enable Bluetooth, exit the app.
                    Toast.makeText(this, R.string.bt_not_enabled_leaving,
                            Toast.LENGTH_SHORT).show();
                    finish();
                }

            default:
                super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void setupFragments() {
        /*FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        ScannerFragment scannerFragment = new ScannerFragment();
        // Fragments can't access system services directly, so pass it the BluetoothAdapter
        scannerFragment.setBluetoothAdapter(mBluetoothAdapter);
        transaction.replace(R.id.scanner_fragment_container, scannerFragment);

        AdvertiserFragment advertiserFragment = new AdvertiserFragment();
        transaction.replace(R.id.advertiser_fragment_container, advertiserFragment);

        transaction.commit(); */
    }

    private void accessPairing(View view) {
        pairingButton = (Button)findViewById(R.id.pairingButton);
        pairingButton.setOnClickListener(new View.OnClickListener);
    }

    private void showErrorText(int messageId) {

        TextView view = (TextView) findViewById(R.id.error_textview);
        view.setText(getString(messageId));
    }
}
