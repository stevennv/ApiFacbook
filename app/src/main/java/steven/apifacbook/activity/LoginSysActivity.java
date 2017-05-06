package steven.apifacbook.activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import steven.apifacbook.R;

public class LoginSysActivity extends AppCompatActivity implements View.OnClickListener {
    private Toolbar toolbar;
    private TextView tvTitleToolbar;
    private EditText edtUserName;
    private EditText edtPass;
    private Button btnLogin;
    private String userName;
    private String pass;
    private ProgressDialog mProgressDialog;
    private static final String URL = "http://sis.hust.edu.vn/";
    private static final String URL2 = "http://sis.hust.edu.vn/ModuleUser/vLogin.aspx";
    HashMap<String, String> cookies = new HashMap<>();
    HashMap<String, String> formData = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sys);
        iniUI();
    }

    protected void iniUI() {
        mProgressDialog = new ProgressDialog(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        edtUserName = (EditText) findViewById(R.id.edt_user_name);
        edtPass = (EditText) findViewById(R.id.edt_pass);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvTitleToolbar.setText("Login to Sys");
        userName = edtUserName.getText().toString();
        pass = edtPass.getText().toString();
        new getCapcha().execute();
//        loginToSys();
        btnLogin.setOnClickListener(this);
    }

    private void loginToSys() throws IOException {
//        try {
//            Connection.Response loginForm= Jsoup.connect(URL).method(Connection.Method.GET).execute();
//            Document document = loginForm.parse();
//            cookies.putAll(loginForm.cookies());
////            String authToken =document.select()
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Connection.Response loginPageResponse = Jsoup.connect(URL).timeout(10 * 1000).execute();
        Map<String, String> mapLoginPageCookies = loginPageResponse.cookies();
        Map<String, String> mapParams = new HashMap<String, String>();
        mapParams.put("ctl00$cLogIn1$tb_cLogIn_User", userName);
        mapParams.put("ctl00$cLogIn1$tb_cLogIn_Pass", pass);
        mapParams.put("ctl00$cLogIn1$bt_cLogIn", "readonly");
        Connection.Response responsePostLogin = Jsoup.connect(URL)
                //referrer will be the login page's URL
//                .referrer("https://mail.rediff.com/cgi-bin/login.cgi")
                //user agent
//                .userAgent("Mozilla/5.0")
                //connect and read time out
                .timeout(10 * 1000)
                //post parameters
                .data(mapParams)
                //cookies received from login page
                .cookies(mapLoginPageCookies)
                //many websites redirects the user after login, so follow them
                .followRedirects(true)
                .execute();
        Document document = responsePostLogin.parse();
//        System.out.println(document);
        Log.e("12333333", String.valueOf(document));
        //get the cookies
        Map<String, String> mapLoggedInCookies = responsePostLogin.cookies();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                try {
                    loginToSys();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private class getCapcha extends AsyncTask<Void, Void, Void> {
        private String a;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog.setMessage("Please Wait!!!");
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Document document = Jsoup.connect(URL2).get();
                Element element = document.getElementById("MainContent_ccCaptcha_IMG");
                a = element.attr("src");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
//            super.onPostExecute(aVoid);
            ImageView imgCode = (ImageView) findViewById(R.id.img_code);
            Glide.with(LoginSysActivity.this).load(URL + a).into(imgCode);
            mProgressDialog.dismiss();
        }
    }
}
