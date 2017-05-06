package steven.apifacbook.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import steven.apifacbook.R;
import steven.apifacbook.adapter.ListFriendAdapter;
import steven.apifacbook.adapter.TimelineAdapter;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.ImageData;
import steven.apifacbook.model.InfoFriendData;
import steven.apifacbook.model.InfoFriendList;
import steven.apifacbook.model.timeline.TimeLine;
import steven.apifacbook.model.timeline.TimelineData;
import steven.apifacbook.ultis.ConnectionUtils;


public class ListFriendActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTitleToolbar;
    private RecyclerView rvListFriend;
    private TimelineAdapter adapter;
    //    private PhotoListAdapter photoListAdapter;/
    private InfoFriendList infoFriendList;
    private TextView tvNumberFriends;
    //    private InfoPhotos photos;
    private List<ImageData> dataList;
    //    private List<InfoPhotos> photosList;
    private String json;
    //    private Data data;
    private String check;
    private String idAnother;
    private List<TimelineData> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_friend);
        iniUI();
    }

    protected void iniUI() {
        Intent intent = getIntent();
        check = intent.getStringExtra("1");


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvNumberFriends = (TextView) findViewById(R.id.tv_number_friends);
        tvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        rvListFriend = (RecyclerView) findViewById(R.id.rv_list_friend);
        tvTitleToolbar.setText("List Friend");
//        photoListAdapter = new PhotoListAdapter(this, photosList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        rvListFriend.setLayoutManager(mLayoutManager);
        rvListFriend.setItemAnimator(new DefaultItemAnimator());
//        rvListFriend.setAdapter(adapter);
        LoginManager.getInstance().logInWithReadPermissions(
                this,
                Arrays.asList("user_friends"));
//        getFriendList();
        Log.d("1bbbf", check + "   ");
        getTimeline();
//        if (check == Common.ME) {
//            getPhotoMe();
//        } else if (check == Common.ANOTHER) {

//            getPhotoAnother();
//        }
    }

    private void getFriendList() {
        /* make the API call */
        Log.d("123213123213", AccessToken.getCurrentAccessToken().getToken() + "  ");
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/me/friends",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        Log.d("FRIENDS_LIST", "   \n" +
                                response.getJSONObject());
                        try {
                            JSONObject jsonObj = new JSONObject(response.getJSONObject().toString());
                            JSONArray listFriends = jsonObj.getJSONArray("data");
                            for (int i = 0; i < listFriends.length(); i++) {
                                JSONObject data = listFriends.getJSONObject(i);

                            }
                            JSONObject totalCount = jsonObj.getJSONObject("summary");
                            String numberFriend = totalCount.getString("total_count");
                            tvNumberFriends.setText("Bạn có " + numberFriend + " người bạn");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).executeAsync();
    }

    private void getTimeline() {
        if (ConnectionUtils.isOnline(this)) {
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me/feed?fields=name,description,created_time,status_type,full_picture",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            String json = response.getRawResponse();
                            Gson gson = new Gson();
                            TimeLine timeLine = gson.fromJson(json, TimeLine.class);
                            list = timeLine.getData();
                            Log.e("LOG_JSON", json);
                            adapter = new TimelineAdapter(ListFriendActivity.this, list);
                            adapter.notifyDataSetChanged();
                            rvListFriend.setAdapter(adapter);
                        }
                    }
            ).executeAsync();
        } else {
            Toast.makeText(this, "Vui lòng kiểm tra kết nối mạng", Toast.LENGTH_SHORT).show();
        }

    }
}
