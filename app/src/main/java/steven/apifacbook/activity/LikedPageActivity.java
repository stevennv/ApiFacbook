package steven.apifacbook.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import steven.apifacbook.R;
import steven.apifacbook.adapter.LikedPageAdapter;
import steven.apifacbook.model.LikedPage.LikedPageData;
import steven.apifacbook.model.likedpage2.Data;
import steven.apifacbook.model.likedpage2.Datum;
import steven.apifacbook.ultis.ConnectionUtils;
import steven.apifacbook.ultis.EndlessRecyclerViewScrollListener;

public class LikedPageActivity extends AppCompatActivity {
    private RecyclerView rvLikedPage;
    private LikedPageAdapter adapter;
    private List<Datum> list;
    private Toolbar toolbar;
    private TextView tvTitleToolbar;
    private boolean isCroll = false;
    private GridLayoutManager mLayoutManager;
    private String URL2;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_page);
        iniUI();
    }

    protected void iniUI() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitleToolbar.setText("Liked Page");
        rvLikedPage = (RecyclerView) findViewById(R.id.rv_list_page);
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvLikedPage.setLayoutManager(mLayoutManager);
        rvLikedPage.setItemAnimator(new DefaultItemAnimator());
        getListLikedPage();
        rvLikedPage.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (isCroll) {
                    Log.d("USUSUSUSSSS", "OKOKO");
                    loadMoreLikedPage();
                }
            }
        });
        createNotification();

    }

    private void loadMoreLikedPage() {
        if (URL2 == null || URL2.equals("")) {

        } else {
            progressBar.setVisibility(View.VISIBLE);
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(URL2)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Log.d("1231232131231", json);
                    Gson gson = new Gson();
                    Data data = gson.fromJson(json, Data.class);
                    List<Datum> data1 = data.getData();
                    list.addAll(data1);

                    URL2 = data.getPaging().getNext();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                }
            });
        }

    }

    private void getListLikedPage() {
        if (ConnectionUtils.isOnline(this)) {
            new GraphRequest(
                    AccessToken.getCurrentAccessToken(),
                    "/me/likes?fields=name,picture&limit=20",
                    null,
                    HttpMethod.GET,
                    new GraphRequest.Callback() {
                        public void onCompleted(GraphResponse response) {
                            String json = response.getRawResponse();
                            Log.e("123789", json + "   ");
                            Gson gson = new Gson();
                            Data p = gson.fromJson(json, Data.class);
                            for (int i = 0; i < p.getData().size(); i++) {
                                Log.e("LIKED_PAGE", "  " + p.getData().get(i).getId());
                                list = p.getData();
                                URL2 = p.getPaging().getNext();
                                Log.d("GET_NEXT", p.getPaging().getNext());
                                adapter = new LikedPageAdapter(getApplicationContext(), list);
                                adapter.notifyDataSetChanged();
                                rvLikedPage.setAdapter(adapter);
                                int size = list.size();
                                if (size >= 20) {
                                    isCroll = true;
                                } else {
                                    isCroll = false;
                                }
                            }
//                        for (int i = 0; i < p.getLikedPage().size(); i++) {
//                            Log.d("123123123123123213", p.getLikedPage().get(i).getLikedPageDatas().getName() + "   ");
//                            data = p.getLikedPage().get(i).getLikedPageDatas();

//                    }

            /* handle the result */
                        }
                    }

            ).

                    executeAsync();
        } else {
            Toast.makeText(this, getResources().getString(R.string.check_connection), Toast.LENGTH_SHORT).show();
        }

    }

    private void createNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setSmallIcon(R.drawable.ic_picture);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_menu_send));
        builder.setContentTitle("Thông báo à");
        builder.setContentText("Ừ! Đúng rồi");
        builder.setSubText("Steven");
        builder.setAutoCancel(false); // Tự động cancel khi ấn vào notification
        builder.setContentIntent(pendingIntent);
        manager.notify(1, builder.build());
    }
}
