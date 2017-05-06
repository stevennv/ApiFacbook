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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import steven.apifacbook.R;
import steven.apifacbook.activity.ListFriendUsesAppActivity;
import steven.apifacbook.activity.ShowPhotoActivity;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.imagesAlbum.Datum;

/**
 * Created by TruongNV on 4/4/2017.
 */

public class ImageAlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Datum> list;
    private String json;

    public ImageAlbumAdapter(Context context, List<Datum> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_image, parent, false);
        viewHolder = new ImageAlbumAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Glide.with(context).load(list.get(position).getImages().get(0).getSource()).into(myViewHolder.imgAvatar);

        if (list.get(position).getLikes() == null) {
            myViewHolder.tvNumberLike.setText("Chưa có ai thích nội dung này");
        } else {
            Log.d("COUNT_LIKESS", list.get(position).getLikes().getData().size() + " ");
            myViewHolder.tvNumberLike.setText(list.get(position).getLikes().getData().size() + " người thích nội dung này");
        }
        if (list.get(position).getName() == null) {

        } else {
            myViewHolder.tvName.setText(list.get(position).getName());
        }

        myViewHolder.llFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowPhotoActivity.class);
                intent.putExtra(Common.ALBUM_ID, list.get(position).getId());
                intent.putExtra(Common.linkPhoto, list.get(position).getImages().get(0).getSource());
                intent.putExtra(Common.CHECK_ACTIVITY, "1");
                Gson gson = new Gson();
                json = gson.toJson(list);
                intent.putExtra(Common.LIST_IMAGE, json);
                context.startActivity(intent);
            }
        });
        myViewHolder.tvNumberLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getLikes() == null) {
                    Toast.makeText(context, "Chưa có ai thích nội dung này", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(context, ListFriendUsesAppActivity.class);
                    intent.putExtra(Common.CHECK_ACTIVITY, "1");
                    intent.putExtra(Common.ALBUM_ID, list.get(position).getId());
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgAvatar;
        public TextView tvName;
        public TextView tvNumberLike;
        public LinearLayout llFriends;

        public MyViewHolder(View itemView) {
            super(itemView);
            llFriends = (LinearLayout) itemView.findViewById(R.id.ll_details_album);
            imgAvatar = (ImageView) itemView.findViewById(R.id.img_detail_album);
            tvName = (TextView) itemView.findViewById(R.id.tv_des);
            tvNumberLike = (TextView) itemView.findViewById(R.id.tv_number_likes);
        }
    }
}
