//package nordberg.katie.hw2;
//
//import android.app.Activity;
//import android.content.pm.PackageManager;
//import android.util.Log;
//
//import androidx.annotation.NonNull;
//import androidx.core.app.ActivityCompat;
//
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.tasks.OnSuccessListener;
//
//public class TaskThread extends Activity {
////    @Override
////    public void run(){
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
//    FusedLocationProviderClient fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
//            Log.d("debug", "Permission granted");
////            fusedLocationClient.getLastLocation().addOnSuccessListener(new LocListener());
//            fusedLocationClient.getLastLocation().addOnSuccessListener(
//                new OnSuccessListener<Location>() {
//                    @Override
//                    public void onSuccess(Location location) {
//                        Log.d("debug", "Success getting LocListener");
//                    }
//                }
//            );
//        }
//    }
//}
////TaskThread tt = new TaskThread();
////tt.run();