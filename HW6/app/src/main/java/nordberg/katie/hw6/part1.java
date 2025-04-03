package nordberg.katie.hw6;

import android.app.Activity;
import android.app.AlertDialog;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class part1 extends Activity {
    private final MainActivity ma;
    public part1(MainActivity ma) { this.ma = ma; }
    @JavascriptInterface
    public void androidFunction(){
        // This is how we displayed a message in the example from class
        // AlertDialog.Builder adb = new AlertDialog.Builder(ma);
        // adb.setMessage("Hey hey hey");
        // adb.show();

        // This is how to display a message with Toast.
        // The trick is to pass the MainActivity as the context
        String text = "Called from webview";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(this.ma, text, duration);
        toast.show();
    }
}
