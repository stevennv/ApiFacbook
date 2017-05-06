package steven.apifacbook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import steven.apifacbook.R;
import steven.apifacbook.adapter.ImageAlbumAdapter;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.imagesAlbum.Datum;
import steven.apifacbook.model.imagesAlbum.ListImage;
import steven.apifacbook.ultis.EndlessRecyclerViewScrollListener;

public class ListImageAlbumActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTitleToolbar;
    private RecyclerView rvListImageAlbum;
    private String idAlbum;
    private String title;
    private ImageAlbumAdapter adapter;
    private List<Datum> list;
    private GridLayoutManager layoutManager;
    private String urlLoadMore;
    private ProgressBar progressBar;
    private boolean isCroll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_image_album);
        iniUI();
    }

    protected void iniUI() {
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        Intent intent = getIntent();
        idAlbum = intent.getStringExtra(Common.ALBUM_ID);
        title = intent.getStringExtra(Common.TITLE);
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
        tvTitleToolbar.setText(title);
        rvListImageAlbum = (RecyclerView) findViewById(R.id.rv_list_image_album);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvListImageAlbum.setHasFixedSize(true);
        rvListImageAlbum.setLayoutManager(layoutManager);
        getListImageAlbum(idAlbum);
        rvListImageAlbum.addOnScrollListener(new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                if (isCroll) {
                    loadMoreImageAlbum();
                }
            }
        });

    }

    private void getListImageAlbum(String idAlbum) {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + idAlbum + "/photos?fields=name,likes,images&limit=10",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
            /* handle the result */
                        String json = response.getRawResponse();
                        Log.e("TEST_JSON_1", json);
                        Gson gson = new Gson();
                        ListImage data = gson.fromJson(json, ListImage.class);
                        for (int i = 0; i < data.getData().size(); i++) {
                            list = data.getData();
                            adapter = new ImageAlbumAdapter(ListImageAlbumActivity.this, list);
                            urlLoadMore = data.getPaging().getNext();
                            rvListImageAlbum.setAdapter(adapter);
                            int size = list.size();
                            if (size >= 10) {
                                isCroll = true;
                            } else {
                                isCroll = false;
                            }
                        }

                    }
                }
        ).executeAsync();
    }

    private void loadMoreImageAlbum() {
        if (urlLoadMore == null || urlLoadMore.equals("")) {

        } else {
            progressBar.setVisibility(View.VISIBLE);
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlLoadMore)
                    .build();
            okHttpClient.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String json = response.body().string();
                    Gson gson = new Gson();
                    ListImage data = gson.fromJson(json, ListImage.class);
                    List<Datum> list1 = data.getData();
                    list.addAll(list1);
                    urlLoadMore = data.getPaging().getNext();
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
}
