package nordberg.katie.hw6;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onCreate(Bundle bundle){
        String url = "https://drew.davidson.cool/mobsec/?h=web";
        super.onCreate(bundle);
        this.setContentView(R.layout.my_layout);

        WebView wv = this.findViewById(R.id.coolview);
        // This creates a bridge from JavaScript to call Android code.
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setDomStorageEnabled(true);  // Enables local storage for WebView
        wv.getSettings().setAllowContentAccess(true);
        wv.getSettings().setAllowFileAccess(true);

        wv.addJavascriptInterface(new part1(this), "Android");

        wv.setWebChromeClient(new WebChromeClient());
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        wv.loadUrl(url);

        Button b = this.findViewById(R.id.coolButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The example from class
                // wv.loadUrl("javascript:(function f(){console.log('Hello from console'); document.body.innerHTML += '<input type=\"button\" value=\"Click Me!\" onclick=\"location.href=\'https://en.wikipedia.org/wiki/Cross-site_scripting\'\"></input>';})()");
                wv.loadUrl("javascript:(function f() {" +
                        "console.log('Hello from console');" +
                        "document.body.innerHTML += '<form><button formaction=\"https://en.wikipedia.org/wiki/Cross-site_scripting\">Click Me!</button></form>';" +
                        "})()");
            }
        });
    }
}