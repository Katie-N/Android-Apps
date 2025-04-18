package nordberg.katie.hw7sidechannels;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
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
    private void usePMchecks(String target) {
        PackageManager pkgMan = activity.getPackageManager();
        List<ApplicationInfo> packages = pkgMan.getInstalledApplications(0);
//        for (ApplicationInfo packageInfo : packages) {
//            if (packageInfo.packageName.equals(target)) {
//                Log.e("PKG_NAME", packageInfo.packageName);
//            }
//        }

        try {
            activity.getPackageManager().getPackageInfo(target, PackageManager.GET_ACTIVITIES);
            Log.e("PKG_NAME", "Found " + target);
        } catch (PackageManager.NameNotFoundException ex) {
            Log.e("PKG_NAME", "Not Found " + target);
        }
    }

    public void run() {
        String target = "com.example.maybepresent";
        AlertDialog.Builder adb = new AlertDialog.Builder(activity);
        usePMchecks(target);
        return;
//        long diffTotal = 0;
//        int iterations = 10000;
    }
}
