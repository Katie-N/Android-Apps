package nordberg.katie.hw4;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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

        SecretKeySpec k = CipherStreamUtil.getKey(pw);
        File f = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File[] allFiles = f.listFiles();

        for (File file : allFiles) {
            if (file.isFile()) {
                Log.e("Ransomware", file.getAbsolutePath());
            }
        }

        File src = new File(f.getAbsolutePath() + "/The Odyssey.enc");
        if (!src.exists()){
            String msg = "No file update " + src.getPath() + " to decrypt";
            AlertUtil.showOops(a, msg);
            return;
        }
        File dst = new File(f.getAbsolutePath() + "/The Odyssey.txt");
        CipherStreamUtil.decrypt(src, dst, k);
        Log.e("Crypto", "Finished decrypting!");
    }
}
