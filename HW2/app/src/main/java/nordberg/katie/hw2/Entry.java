package nordberg.katie.hw2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.Manifest;

import java.util.Arrays;


// Use this class for the startup stuff.
// The app will go to this class first due to the Activity I added in the Manifest.
public class Entry extends Activity {

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);

        // Make a list of the permissions we want to ask for
        String[] PermsList = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        Log.d("Katie", "Request permissions");
        // We have to request these permissions in the app too because they are dangerous
        this.requestPermissions(PermsList, 7);
    }

    // When we get a response from the user regarding dynamic permissions, this is called
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Log.d("Katie", Arrays.toString(grantResults));

        // Make an instance of the TaskThread class I made that will handle the actual hard work
        // It's important to not clog the main thread (the UI thread) with stuff that can be done in the background
        // I pass the context to the thread only because the context is needed for some of the function calls.
        TaskThread tt = new TaskThread(this);
        tt.run();

    }

}
