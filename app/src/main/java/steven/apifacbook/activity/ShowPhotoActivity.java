package steven.apifacbook.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liuguangqiang.swipeback.SwipeBackLayout;

import java.io.File;
import java.util.List;

import steven.apifacbook.R;
import steven.apifacbook.adapter.ShowImageAdapter;
import steven.apifacbook.common.Common;
import steven.apifacbook.dialog.ChooseNameDialog;
import steven.apifacbook.model.download.DownLoad;
import steven.apifacbook.model.imagesAlbum.Datum;
import steven.apifacbook.model.imagesAlbum.Image;
import steven.apifacbook.ultis.BasicImageDownloader;
import steven.apifacbook.ultis.DatabaseHandler;
import steven.apifacbook.ultis.TouchImageView;

public class ShowPhotoActivity extends AppCompatActivity implements View.OnClickListener {
    private TouchImageView imgDetail;
    private ImageView imgClose;
    private ImageView imgDownload;
    private String urlImage;
    private SwipeBackLayout sbLayout;
    private String check = "1";
    private ViewPager vpListImages;
    private List<Datum> listAlbum;
    private List<Image> listPhoto;
    private ShowImageAdapter adapter;
    private String imageName;
    private File myImageFile;
    private DatabaseHandler handler;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_show_photo);
        iniUI();
    }

    protected void iniUI() {
        Intent intent = getIntent();
//        sbLayout = (SwipeBackLayout) findViewById(R.id.sb_layout);
        imageName = intent.getStringExtra(Common.ALBUM_ID);
//        Log.d("TEST_VIEWPAGER", listAlbum.get(0).getName()+ "   ");
        urlImage = intent.getStringExtra(Common.linkPhoto);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        check = intent.getStringExtra(Common.CHECK_ACTIVITY);
        imgDetail = (TouchImageView) findViewById(R.id.img_image_details);
        imgClose = (ImageView) findViewById(R.id.img_close);
        imgDownload = (ImageView) findViewById(R.id.img_download);
        imgClose.setOnClickListener(this);
        imgDownload.setOnClickListener(this);
        Glide.with(this).load(urlImage).into(imgDetail);
        if (check.equals("0")) {
            imgDownload.setVisibility(View.GONE);
        } else {
        }
//        setDragEdge(SwipeBackLayout.DragEdge.BOTTOM);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_close:
                finish();
                break;
            case R.id.img_download:
                downloadImage(imageName);
//                ChooseNameDialog dialog = new ChooseNameDialog(this, urlImage);
//                dialog.show();
                break;
        }
    }

    private void downloadImage(final String name) {
        progressBar.setVisibility(View.VISIBLE);
        handler = new DatabaseHandler(ShowPhotoActivity.this);
        final BasicImageDownloader downloader = new BasicImageDownloader(new BasicImageDownloader.OnImageLoaderListener() {
            @Override
            public void onError(BasicImageDownloader.ImageError error) {
                Toast.makeText(ShowPhotoActivity.this, "Error code " + error.getErrorCode() + ": " +
                        error.getMessage(), Toast.LENGTH_LONG).show();
                error.printStackTrace();
                Log.d("LOG_ERROR", error.getMessage());
            }

            @Override
            public void onProgressChange(int percent) {
            }

            @Override
            public void onComplete(Bitmap result) {
                        /* save the image - I'm gonna use JPEG */
                final Bitmap.CompressFormat mFormat = Bitmap.CompressFormat.PNG;
                        /* don't forget to include the extension into the file name */
                myImageFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() +
                        File.separator + "ApiFb" + File.separator + name + "." + mFormat.name().toLowerCase());


                BasicImageDownloader.writeToDisk(myImageFile, result, new BasicImageDownloader.OnBitmapSaveListener() {
                    @Override
                    public void onBitmapSaved() {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(ShowPhotoActivity.this, "Image saved as: " + myImageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBitmapSaveError(BasicImageDownloader.ImageError error) {
                        Toast.makeText(ShowPhotoActivity.this, "Error code " + error.getErrorCode() + ": " +
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }


                }, mFormat, false);
                Log.d("SIZE_LOG", String.valueOf(myImageFile.getAbsolutePath()) + "\n" + myImageFile.toString());
                handler.addContact(new DownLoad(name, myImageFile.toString()));
            }
        });
        downloader.download(urlImage, true);


    }
}
