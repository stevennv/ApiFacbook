package steven.apifacbook.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.List;

import steven.apifacbook.R;
import steven.apifacbook.activity.ShowPhotoActivity;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.download.DownLoad;
import steven.apifacbook.ultis.DatabaseHandler;

/**
 * Created by TruongNV on 4/9/2017.
 */

public class DownloadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<DownLoad> list;
    private DatabaseHandler handler;
    private static boolean delete = true;

    public DownloadAdapter(Context context, List<DownLoad> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_download, parent, false);
        viewHolder = new HolderDownload(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        handler = new DatabaseHandler(context);
        HolderDownload holderDownload = (HolderDownload) holder;
        holderDownload.tvName.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getUriFile()).placeholder(R.drawable.ic_picture).into(holderDownload.imgThumbnail);
        holderDownload.llDownLoad.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.d("LOG_DELETE", " " + list.get(position).getUriFile());
                AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                dialog.setTitle("Do you want delete this file ?").setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        handler.deleteContact(list.get(position));
                        list.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, list.size());
//                        File file = new File(list.get(position).getUriFile());
//                        delete = file.delete();

                        dialog.dismiss();
                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });
        holderDownload.llDownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowPhotoActivity.class);
                intent.putExtra(Common.linkPhoto, list.get(position).getUriFile());
                intent.putExtra(Common.CHECK_ACTIVITY, "0");
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HolderDownload extends RecyclerView.ViewHolder {
        public TextView tvName;
        public LinearLayout llDownLoad;
        public ImageView imgThumbnail;

        public HolderDownload(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            llDownLoad = (LinearLayout) itemView.findViewById(R.id.ll_download);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
        }
    }
}
