package steven.apifacbook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.tfc.webviewer.ui.widget.WebViewer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import im.delight.android.webview.AdvancedWebView;

public class WebviewActivity extends AppCompatActivity {

    private WebViewer webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView = (WebViewer) findViewById(R.id.webview);
        webView.getSettings();
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.loadUrl("http://www.phimmoi.net");
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                webView.loadUrl("javascript:window.HTMLOUT.processHTML('<head>'+document.getElementsByTagName('html')[0].innerHTML+'</head>');");
                return false;
            }
        });
        webView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
    }

    class MyJavaScriptInterface {
        @JavascriptInterface
        public void processHTML(String html) {

            Document document = Jsoup.parse(html);
            Elements metaElems = document.select("video");
            String linkGet = metaElems.attr("src");
            Log.d("789264546", " LinkGET1  2      " + linkGet);
//            Log.d("TETEQWEQW", value);
//            if (!value.isEmpty()) {
//                listUrl.add(value);
//            }

        }
    }
}
