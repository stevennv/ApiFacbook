package steven.apifacbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
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


import de.hdodenhof.circleimageview.CircleImageView;
import steven.apifacbook.R;
import steven.apifacbook.WebviewActivity;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.UserInfo;
import steven.apifacbook.ultis.SharePreferenceUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private CircleImageView civAvatar;
    private TextView tvName;
    private ImageView imgBackGround;
    private SharePreferenceUtils utils;
    private UserInfo userInfo;
    private String name;
    private String avatar;
    private String id;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.setApplicationId(getResources().getString(R.string.facebook_app_id));
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        utils = new SharePreferenceUtils(this);

        userInfo = new UserInfo(name, avatar, id);
        loginButton = (LoginButton) findViewById(R.id.btn_login_fb);
        loginButton.setReadPermissions("email");
        loginButton.setReadPermissions("user_friends");
        loginButton.setReadPermissions("public_profile");
//        loginButton.setReadPermissions("ads_management");
//        loginButton.setReadPermissions("manage_pages");
//        loginButton.setReadPermissions("publish_pages");
//        loginButton.setReadPermissions("business_management");
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View view = navigationView.getHeaderView(0);
        civAvatar = (CircleImageView) view.findViewById(R.id.imageView);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        imgBackGround = (ImageView) view.findViewById(R.id.img_background);
        navigationView.setNavigationItemSelectedListener(this);
        updateUI();
        callbackManager = CallbackManager.Factory.create();
        loginButton.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            this.finishAffinity();
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            if (AccessToken.getCurrentAccessToken() == null) {
                Toast.makeText(this, "Mời bạn đăng nhập", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, WebviewActivity.class);
                intent.putExtra("1", Common.ME);
                startActivity(intent);
            }

        } else if (id == R.id.nav_gallery) {
            if (AccessToken.getCurrentAccessToken() == null) {
                Toast.makeText(this, "Mời bạn đăng nhập", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, LikedPageActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_slideshow) {
            if (AccessToken.getCurrentAccessToken() == null) {
                Toast.makeText(this, "Mời bạn đăng nhập", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, MyWallActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_manage) {
            if (AccessToken.getCurrentAccessToken() == null) {
                Toast.makeText(this, "Mời bạn đăng nhập", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, ListFriendUsesAppActivity.class);
                intent.putExtra(Common.CHECK_ACTIVITY, "2");
                startActivity(intent);
            }
        } else if (id == R.id.nav_share) {
            if (AccessToken.getCurrentAccessToken() == null) {
                Toast.makeText(this, "Mời bạn đăng nhập", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, DownloadedActivity.class);
                startActivity(intent);
            }
        } else if (id == R.id.nav_send) {
            if (AccessToken.getCurrentAccessToken() == null) {
                Toast.makeText(this, "Mời bạn đăng nhập", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, MusicActivity.class);
                startActivity(intent);
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void updateUI() {
        if (AccessToken.getCurrentAccessToken() != null) {
            tvName.setText(utils.getNameUser());
            String url = "http://graph.facebook.com/"
                    + utils.getIdUser() + "/picture?type=large";
            Glide.with(this).load(url).into(civAvatar);
            Glide.with(this).load(url).into(imgBackGround);
            imgBackGround.setAlpha(125);
        } else {
            tvName.setText("Mời bạn đang nhập");
        }

    }

    private void loginFb() {
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                Glide.with(MainActivity.this).load("http://graph.facebook.com/"
                        + loginResult.getAccessToken().getUserId() + "/picture?type=large").into(civAvatar);
                tvName.setText(profile.getName());
                Glide.with(MainActivity.this).load("http://graph.facebook.com/"
                        + loginResult.getAccessToken().getUserId() + "/picture?type=large").into(imgBackGround);
                imgBackGround.setAlpha(125);
                utils.saveIdUser(profile.getId());
                utils.saveNameUser(profile.getName());
                name = profile.getName();
                avatar = "http://graph.facebook.com/"
                        + loginResult.getAccessToken().getUserId() + "/picture?type=large";
                id = profile.getId();

                Log.d("IDID", loginResult.getAccessToken().getUserId() + "  ");
                userInfo = new UserInfo(name, avatar, id);

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_fb:
                loginFb();
                break;
        }
    }


}
