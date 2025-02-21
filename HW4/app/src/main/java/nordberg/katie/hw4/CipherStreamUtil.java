package nordberg.katie.hw4;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class CipherStreamUtil {
    public static String AES_ALGORITHM = "AES";
    public static String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";

    public static SecretKeySpec getKey(String myKey) {
        try {
            MessageDigest sha = null;
            byte[] key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            return new SecretKeySpec(key, AES_ALGORITHM);
        } catch (Exception e){
            Log.e("Crypto", e.toString());
        }
        return null;
    }

    public static void decrypt(File source, File dest, SecretKeySpec spec){
        try {
            Cipher aes2 = Cipher.getInstance(AES_TRANSFORMATION);
            aes2.init(Cipher.DECRYPT_MODE, spec);

            FileInputStream fis = new FileInputStream(source);
            CipherInputStream cin = new CipherInputStream(fis, aes2);

            FileOutputStream fos = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = cin.read(buffer)) > 0){
                fos.write(buffer, 0, bytesRead);
            }

            fos.flush();
            fos.close();
        }
        catch (Exception ex) {
            Log.e("Crypto", ex.toString());
        }
    }

    public static void encrypt(File source, File dest, SecretKeySpec spec){
        try {
            Cipher aes2 = Cipher.getInstance(AES_TRANSFORMATION);
            aes2.init(Cipher.ENCRYPT_MODE, spec);

            FileOutputStream fos = new FileOutputStream(dest);
            CipherOutputStream out = new CipherOutputStream(fos, aes2);

            FileInputStream fis = new FileInputStream(source);

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) > 0){
                out.write(buffer, 0, bytesRead);
            }

            out.flush();
            out.close();
        }
        catch (Exception ex) {
            Log.e("Crypto", ex.toString());
        }
    }
}
