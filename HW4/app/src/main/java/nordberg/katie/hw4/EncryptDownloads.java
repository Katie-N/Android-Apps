package nordberg.katie.hw4;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import javax.crypto.spec.SecretKeySpec;

public class EncryptDownloads {
    private final Activity a;
    private static final String TAG = "EncryptDownloads";

    EncryptDownloads(Activity a) {
        this.a = a;
    }
    String pw = "mobsec";
    SecretKeySpec k = CipherStreamUtil.getKey(pw);

    public void encryptMethod() {
        SecretKeySpec k = CipherStreamUtil.getKey(pw);

        File f = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File src = new File(f.getAbsolutePath() + "/The Odyssey.txt");
        if (!src.exists()){
            String msg = "No file " + src.getPath() + " to encrypt";
            AlertUtil.showOops(a, msg);
            return;
        }
        File dst = new File(f.getAbsolutePath() + "/The Odyssey.enc");
        CipherStreamUtil.encrypt(src, dst, k);
        Log.e("Crypto", "Finished encrypting!");
    }
}
