package nordberg.katie.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class Entry extends Activity {
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        Toast msg = Toast.makeText(this, "Hello World!", Toast.LENGTH_LONG);
        msg.show();
    }
}
