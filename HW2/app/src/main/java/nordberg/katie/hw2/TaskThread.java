package nordberg.katie.hw2;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import android.Manifest;

public class TaskThread extends Thread {
    private Context context;

    // Constructor to accept Context
    public TaskThread(Context context) {
        this.context = context;
    }

    @Override
    public void run(){

        FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(context);

        // Check if we have been granted the needed permissions before proceeding
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Log.d("Katie", "Permission granted");
            // Get the last location and pass it to the anonymous class
            fusedLocationClient.getLastLocation().addOnSuccessListener(
                new OnSuccessListener<>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.d("Katie", "Success getting Thread");
                    }
                }
            );
        }
    }
}