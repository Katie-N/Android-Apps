package nordberg.katie.hw4;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionHelper {
    public static boolean hasStoragePerms(Activity a) {
        int write = ContextCompat.checkSelfPermission(a, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int read = ContextCompat.checkSelfPermission(a, android.Manifest.permission.READ_EXTERNAL_STORAGE);

        if (read != PackageManager.PERMISSION_GRANTED){return false;}
        if (write != PackageManager.PERMISSION_GRANTED){return false;}
        return true;
    }

    // Code is used for the callback
    public static void requestStoragePerms(Activity a, int code){
        Log.e("PERMS", "requesting storage perms...");
        ActivityCompat.requestPermissions(
                a,
                new String[] {
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                },
                code
        );
    }
}
