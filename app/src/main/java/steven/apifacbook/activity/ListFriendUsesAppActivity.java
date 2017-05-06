package steven.apifacbook.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.nodes.Element;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import steven.apifacbook.R;
import steven.apifacbook.adapter.FriendAppAdapter;
import steven.apifacbook.adapter.ListLikeAdapter;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.like.Like;
import steven.apifacbook.model.like.LikesData;
import steven.apifacbook.model.likedpage2.Data;
import steven.apifacbook.model.likedpage2.Datum;
import steven.apifacbook.request.LoadMoreLikedPage;
import steven.apifacbook.ultis.EndlessRecyclerViewScrollListener;

public class ListFriendUsesAppActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String URL = "http://sis.hust.edu.vn/ModuleUser/vLogin.aspx";
    ProgressDialog mProgressDialog;
    private Element element;
    private Toolbar toolbar;
    private TextView tvTitleToolbar;
    private TextView tvItem;
    private ImageView imgCode;
    private RecyclerView rvListFriendApp;
    private TextView tvInfo;
    private List<Datum> list;
    private FriendAppAdapter adapter;
    private String checkActivity;
    private ListLikeAdapter adapter1;
    private String albumId;
    private List<LikesData> listLike;
    private final static String BASE_URL = "https://graph.facebook.com/v2.8/";
    private boolean isCroll;
    private String after;
    private GridLayoutManager layoutManager;

    public ListFriendUsesAppActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_jsoup);
        iniUI();
    }

    protected void iniUI() {
        rvListFriendApp = (RecyclerView) findViewById(R.id.rv_list_friend_app);
        tvInfo = (TextView) findViewById(R.id.tv_info);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        tvItem = (TextView) findViewById(R.id.tv_item);

        tvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);

        layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvListFriendApp.setLayoutManager(layoutManager);
        rvListFriendApp.setItemAnimator(new DefaultItemAnimator());
        Intent intent = getIntent();
        checkActivity = intent.getStringExtra(Common.CHECK_ACTIVITY);
        Log.d("12312312312111", checkActivity + "  ");
        if (checkActivity.equals("2")) {
            tvTitleToolbar.setText("Bạn dùng App");
            getFriendList();
        } else if (checkActivity.equals("1")) {
            tvTitleToolbar.setText("Danh sách");
            albumId = intent.getStringExtra(Common.ALBUM_ID);
            Log.d("12312312312111", albumId);
            getListImageAlbum(albumId);
//            rvListFriendApp.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
//                @Override
//                public void onLoadMore(int page, int totalItemsCount) {
//                    if (isCroll) {
//                        Log.d("9877789", "74523");
//                        loadMoreListLike(albumId, AccessToken.getCurrentAccessToken().getToken(), "0", "25", after);
//                    }
//                }
//            });
        }


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
                            tvInfo.setText("Bạn có " + numberFriend + " người bạn , trong đó có " + listFriends.length() + " người sử dụng app");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        String json = response.getRawResponse();
                        Gson gson = new Gson();
                        Data data = gson.fromJson(json, Data.class);
                        for (int i = 0; i < data.getData().size(); i++) {
                            Log.e("LIKED_PAGE", "  " + data.getData().get(i).getId());
                            list = data.getData();
                            adapter = new FriendAppAdapter(ListFriendUsesAppActivity.this, list);
                            adapter.notifyDataSetChanged();
                            rvListFriendApp.setAdapter(adapter);

                        }
                    }
                }
        ).executeAsync();
    }

    private void getListImageAlbum(String idAlbum) {
        Log.d("32112321", AccessToken.getCurrentAccessToken().getToken());
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + idAlbum + "/likes?limit=25",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */
                        String json = response.getRawResponse();
                        Log.e("TEST_JSON_LIKE", json + " ");
                        Gson gson = new Gson();
                        Like data = gson.fromJson(json, Like.class);
                        after = data.getPaging().getNext();
                        for (int i = 0; i < data.getData().size(); i++) {
                            listLike = data.getData();
                            adapter1 = new ListLikeAdapter(ListFriendUsesAppActivity.this, listLike);
                            adapter1.notifyDataSetChanged();
                            rvListFriendApp.setAdapter(adapter1);
                            if (data.getData().size() < 26) {
                                isCroll = true;
                            } else {
                                isCroll = false;
                            }
                        }

//                        listLike = data.getLikesDatas();
//                        adapter1 = new ListLikeAdapter(ListFriendUsesAppActivity.this, listLike);
//                        adapter1.notifyDataSetChanged();
//                        rvListFriendApp.setAdapter(adapter1);
                    }
                }
        ).executeAsync();
    }

    private void loadMoreListLike(String imagesId, String token, String pretty, String limit, String after) {
        String url_office = URL + imagesId + "/likes?access_token=" + token;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url_office)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoadMoreLikedPage likedPage = retrofit.create(LoadMoreLikedPage.class);
        Call<Like> call = likedPage.getMoreLikedPage(pretty, limit, after);
        call.enqueue(new Callback<Like>() {
            @Override
            public void onResponse(Call<Like> call, Response<Like> response) {
                listLike = response.body().getData();
//                after = response.body().getPaging().getNext();
                adapter1 = new ListLikeAdapter(ListFriendUsesAppActivity.this, listLike);
                adapter1.notifyDataSetChanged();
                rvListFriendApp.setAdapter(adapter1);
            }

            @Override
            public void onFailure(Call<Like> call, Throwable t) {

            }
        });
    }


}

