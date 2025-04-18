package nordberg.katie.hw7sidechannels;

import android.app.Activity;
import android.os.Bundle;

public class Entry extends Activity {
    @Override
    public void onCreate(Bundle b){
        super.onCreate(b);
        Thread t = new SpyThread(this);
        t.start();
    }
}
