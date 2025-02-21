package nordberg.katie.hw4;

import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class AlertUtil {

    public static void showOops(Context context, String msg){
        new AlertDialog.Builder(context)
                .setTitle("Oops!")
                .setMessage(msg)
                .setNeutralButton(android.R.string.ok, null)
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .show();
    }
}
