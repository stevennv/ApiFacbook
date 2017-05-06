package steven.apifacbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import steven.apifacbook.R;
import steven.apifacbook.activity.ListImageAlbumActivity;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.album.PictureData;
import steven.apifacbook.model.wall.WallData;

/**
 * Created by TruongNV on 3/30/2017.
 */

public class WallAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<PictureData> list;

    public WallAdapter(Context context, List<PictureData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_album, parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Log.e("TESST_JSON", list.get(position).getPicture().getData().getUrl() + "   ");
        Glide.with(context).load(list.get(position).getPicture().getData().getUrl()).into(myViewHolder.imgPhoto);
        myViewHolder.tvName.setText(list.get(position).getName());
        myViewHolder.llAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListImageAlbumActivity.class);
                intent.putExtra(Common.ALBUM_ID, list.get(position).getId());
                intent.putExtra(Common.TITLE, list.get(position).getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgPhoto;
        public TextView tvName;
        public LinearLayout llAlbum;


        public MyViewHolder(View itemView) {
            super(itemView);
            imgPhoto = (ImageView) itemView.findViewById(R.id.img_album);
            tvName = (TextView) itemView.findViewById(R.id.tv_album_name);
            llAlbum = (LinearLayout) itemView.findViewById(R.id.ll_album);
        }
    }
}
