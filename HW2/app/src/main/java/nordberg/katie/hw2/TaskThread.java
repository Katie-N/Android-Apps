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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.d("Katie", "Success getting Thread");

                        // Apparently the onSuccess function runs on the main thread by default...
                        // And there is a check that prevents http stuff from happening on the main thread.
                        // So I am making an anonymous thread inside of the onSuccess function
                        new Thread(() -> {
                            try {
                                URL url = new URL("http://10.0.2.2:8080/?lat=" + location.getLatitude() + "&lon=" + location.getLongitude());
                                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                                conn.setDoOutput(true);
                                conn.connect();
                                if (conn.getResponseCode() == 200) {
                                    Log.d("Katie", "It worked!");
                                }
                            } catch (IOException e) {
                                Log.d("Katie", "It didn't work");
                                throw new RuntimeException(e);
                            }
                        }).start();
                    }
                }
            );
        }
    }
}