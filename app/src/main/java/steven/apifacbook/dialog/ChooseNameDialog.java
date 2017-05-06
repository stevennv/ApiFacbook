package steven.apifacbook.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;

import steven.apifacbook.R;
import steven.apifacbook.model.download.DownLoad;
import steven.apifacbook.ultis.BasicImageDownloader;
import steven.apifacbook.ultis.DatabaseHandler;

/**
 * Created by TruongNV on 4/7/2017.
 */

public class ChooseNameDialog extends Dialog {
    private Context context;
    private String url;
    private EditText edtName;
    private Button btnDownload;
    private Button btnCancel;
    private ProgressDialog dialog;
    private long size;
    private File myImageFile;
    private DatabaseHandler handler;


    public ChooseNameDialog(Context context, String url) {
        super(context);
        this.context = context;
        this.url = url;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_name_dialog);
        dialog = new ProgressDialog(context);
        edtName = (EditText) findViewById(R.id.edt_name);
        btnDownload = (Button) findViewById(R.id.btn_download);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtName.getText().toString();
                downloadImage(name);
                dismiss();
            }
        });

    }


    private void downloadImage(final String name) {
        handler = new DatabaseHandler(context);
        final BasicImageDownloader downloader = new BasicImageDownloader(new BasicImageDownloader.OnImageLoaderListener() {
            @Override
            public void onError(BasicImageDownloader.ImageError error) {
                Toast.makeText(context, "Error code " + error.getErrorCode() + ": " +
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
                        Toast.makeText(context, "Image saved as: " + myImageFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onBitmapSaveError(BasicImageDownloader.ImageError error) {
                        Toast.makeText(context, "Error code " + error.getErrorCode() + ": " +
                                error.getMessage(), Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }


                }, mFormat, false);
                Log.d("SIZE_LOG", String.valueOf(myImageFile.getAbsolutePath()) + "\n" + myImageFile.toString());
                handler.addContact(new DownLoad(name, myImageFile.toString()));
            }
        });
        downloader.download(url, true);


    }

}
