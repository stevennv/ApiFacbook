package steven.apifacbook.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import at.grabner.circleprogress.CircleProgressView;
import steven.apifacbook.R;
import steven.apifacbook.model.UserInfo;
import steven.apifacbook.ultis.SharePreferenceUtils;
import steven.apifacbook.ultis.progressviews.CircleProgressBar;
import steven.apifacbook.ultis.progressviews.OnProgressViewListener;

public class SplashActivity extends AppCompatActivity {
    private TextView tvCount;
    private CircleProgressBar cpbPro;
    private FrameLayout layoutProgress;
    private LoginButton btnLogin;
    private SharePreferenceUtils utils;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.setApplicationId(getResources().getString(R.string.facebook_app_id));
        setContentView(R.layout.activity_splash);
        callbackManager = CallbackManager.Factory.create();
        iniUI();
    }

    protected void iniUI() {
        utils = new SharePreferenceUtils(this);
        tvCount = (TextView) findViewById(R.id.tvProgress);
        layoutProgress = (FrameLayout) findViewById(R.id.layoutProgress);
        cpbPro = (CircleProgressBar) findViewById(R.id.circle_progress);
        btnLogin = (LoginButton) findViewById(R.id.btn_login_fb);
        cpbPro.setText(123 + "%");
        cpbPro.setRoundEdgeProgress(true);
        cpbPro.setWidthProgressBarLine(10);
        cpbPro.setTextSize(16);
        initDemo();
        btnLogin.setReadPermissions("email");
        btnLogin.setReadPermissions("user_friends");
        btnLogin.setReadPermissions("public_profile");

    }


    private void initDemo() {
        Log.d("1231231231232112", AccessToken.getCurrentAccessToken() + "  ");
        cpbPro.setProgressIndeterminateAnimation(3000);
        cpbPro.setOnProgressViewListener(new OnProgressViewListener() {
            @Override
            public void onFinish() {
                tvCount.setText("100%");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        layoutProgress.animate().alpha(0).setDuration(200).setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                layoutProgress.setVisibility(View.GONE);

                                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(i);


//                                listView.setLayoutManager(new LinearLayoutManager(SplashActivity.this));
//                                listView.addItemDecoration(new VerticalSpaceItemDecoration(Utils.dpToPx(SplashActivity.this, (int) getResources().getDimension(R.dimen._10dp))));
//                                listView.setAdapter(mlistCategoryAdapter);
                            }
                        });
                    }
                }, 500);

            }

            @Override
            public void onProgressUpdate(float progress) {
                tvCount.setText((int) progress + "%");
            }
        });
    }


}
