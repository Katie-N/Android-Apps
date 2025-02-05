package nordberg.katie.hw2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.Manifest;


public class Entry extends Activity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        String[] PermsList = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        Log.d("debug", "Request permissions");
        this.requestPermissions(PermsList, 7);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d("debug", "Permission result");
    }

}
