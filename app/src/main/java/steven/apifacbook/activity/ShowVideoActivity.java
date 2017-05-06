package steven.apifacbook.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import steven.apifacbook.R;
import steven.apifacbook.common.Common;

public class ShowVideoActivity extends AppCompatActivity  {
    private VideoView vvVideo;
    private String url;
    private Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_video);
        iniUI();
    }

    protected void iniUI() {
        btnDownload = (Button) findViewById(R.id.btn_download);
        url = getIntent().getStringExtra(Common.LINK_VIDEO);
        MediaController mediaController = new MediaController(this);
        vvVideo = (VideoView) findViewById(R.id.vv_video);
        vvVideo.setMediaController(mediaController);
        vvVideo.setVideoPath(url);
        vvVideo.start();
    }
}
