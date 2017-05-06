package steven.apifacbook.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.google.gson.Gson;

import java.util.List;

import steven.apifacbook.R;
import steven.apifacbook.adapter.WallAdapter;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.album.Album;
import steven.apifacbook.model.album.PictureData;

public class AlbumListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView tvTitleToolbar;
    private RecyclerView rvAlbums;
    private GridLayoutManager layoutManager;
    private String pageId;
    private List<PictureData> list;
    private WallAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_list);
        iniUI();
    }

    protected void iniUI() {
        Intent intent = getIntent();
        pageId = intent.getStringExtra(Common.PAGE_ID);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tvTitleToolbar = (TextView) findViewById(R.id.tv_title_toolbar);
        rvAlbums = (RecyclerView) findViewById(R.id.rv_my_album);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvTitleToolbar.setText(getResources().getString(R.string.list_albums));
        layoutManager = new GridLayoutManager(this, 2);
        rvAlbums.setLayoutManager(layoutManager);
        getAlbum();
    }

    private void getAlbum() {
        new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/" + pageId + "/albums?fields=name,picture",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                        String json = response.getRawResponse();
                        Log.e("TESST_JSON", json);
                        Gson gson = new Gson();
                        Album album = gson.fromJson(json, Album.class);
                        list = album.getData();
                        adapter = new WallAdapter(getApplicationContext(), list);
                        rvAlbums.setAdapter(adapter);
                    }
                }
        ).executeAsync();
    }
}
