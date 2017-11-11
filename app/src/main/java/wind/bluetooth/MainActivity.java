package wind.bluetooth;

import android.app.Activity;
import android.bluetooth.*;
import android.os.Bundle;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.widget.*;
import android.view.View;

import java.util.Set;

public class MainActivity extends Activity {
    private BluetoothAdapter mBluetoothAdapter;
    private Button pairingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Checking Bluetooth Settings
        if (savedInstanceState == null) {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            // Is Bluetooth supported on the device?
            if (mBluetoothAdapter != null) {
                // Check if Bluetooth is on
                if (mBluetoothAdapter.isEnabled()) {
                    // Everything is supported and enabled, look for pairing.
                    pairBluetooth();
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

                    // Everything is supported and enabled, look for pairing.
                    pairBluetooth();

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

    private void pairBluetooth() {
        pairingButton = findViewById(R.id.pairingButton);
        pairingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, PairActivity.class);

                startActivity(intent);
            }
        });
    }

    private void showErrorText(int messageId) {

        TextView view = (TextView) findViewById(R.id.error_textview);
        view.setText(getString(messageId));
    }
}
