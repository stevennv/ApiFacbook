package steven.apifacbook.activity;

import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.tabak.fragmentswitcher.FragmentStateArrayPagerAdapter;
import me.tabak.fragmentswitcher.FragmentSwitcher;
import steven.apifacbook.R;
import steven.apifacbook.adapter.WrapViewPagerAdapter;
import steven.apifacbook.fragment.FollowsFragment;
import steven.apifacbook.fragment.MyImageFragment;
import steven.apifacbook.ultis.SharePreferenceUtils;
import steven.apifacbook.ultis.WrapContentViewPager;
import steven.apifacbook.ultis.smarttablayout.SmartTabLayout;

public class MyWallActivity extends AppCompatActivity implements MyImageFragment.OnFragmentInteractionListener {
    private Toolbar toolbar;
    private TextView tvTitleToolbar;
    private SharePreferenceUtils utils;
    private PagerSlidingTabStrip tabStrip;
    private ViewPager viewPager;
    private CircleImageView civAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wall);
        iniUI();
    }

    protected void iniUI() {
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.pager_tab_strip);
        utils = new SharePreferenceUtils(this);
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
        civAvatar = (CircleImageView) findViewById(R.id.civ_avatar);
        final String url = "http://graph.facebook.com/"
                + utils.getIdUser() + "/picture?type=large";
        Glide.with(this).load(url).into(civAvatar);
        tvTitleToolbar.setText(utils.getNameUser());
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabStrip.setViewPager(viewPager);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLES = {"My Album", "My Video"};

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    MyImageFragment fragment1 = new MyImageFragment();
                    return fragment1;
                case 1:
                    FollowsFragment fragment2 = new FollowsFragment();
                    return fragment2;
            }
            return null;
        }
    }

}
