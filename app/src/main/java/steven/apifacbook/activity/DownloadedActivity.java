package steven.apifacbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import steven.apifacbook.R;
import steven.apifacbook.adapter.DownloadAdapter;
import steven.apifacbook.model.download.DownLoad;
import steven.apifacbook.ultis.DatabaseHandler;

public class DownloadedActivity extends AppCompatActivity {
    private RecyclerView rvDownload;
    private Toolbar toolbar;
    private TextView tvTitleToolbar;
    private DownloadAdapter adapter;
    private List<DownLoad> list;
    private GridLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_downloaded);
        iniUI();
    }

    protected void iniUI() {
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
        tvTitleToolbar.setText("Downloaded");
        rvDownload = (RecyclerView) findViewById(R.id.rv_download);
        mLayoutManager = new GridLayoutManager(getApplicationContext(), 1);
        rvDownload.setLayoutManager(mLayoutManager);
        rvDownload.setItemAnimator(new DefaultItemAnimator());
        testSQLite();
    }


    private void testSQLite() {
        DatabaseHandler handler = new DatabaseHandler(this);
        List<DownLoad> downLoads = handler.getAllContacts();
        adapter = new DownloadAdapter(this, downLoads);
        adapter.notifyDataSetChanged();
        rvDownload.setAdapter(adapter);
    }
}
