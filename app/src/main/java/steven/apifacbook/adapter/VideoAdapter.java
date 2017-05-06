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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import steven.apifacbook.R;
import steven.apifacbook.activity.ShowVideoActivity;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.video.Video;
import steven.apifacbook.model.video.VideoData;

/**
 * Created by TruongNV on 4/13/2017.
 */

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<VideoData> list;

    public VideoAdapter(Context context, List<VideoData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_video, parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder viewHolder = (MyViewHolder) holder;
        final VideoData data = list.get(position);
        viewHolder.tvTime.setText(convertTime(data.getLength()) + "s");
        viewHolder.tvName.setText(data.getDescription());
        for (int i = 0; i < data.getThumbnails().getData().size(); i++) {
            Glide.with(context).load(data.getThumbnails().getData().get(i).getUri()).into(viewHolder.imgThumbnail);
        }
        viewHolder.rlVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ShowVideoActivity.class);
                intent.putExtra(Common.LINK_VIDEO, data.getSource());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgThumbnail;
        public TextView tvName;
        public RelativeLayout rlVideo;
        public TextView tvTime;


        public MyViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvName = (TextView) itemView.findViewById(R.id.tv_des);
            rlVideo = (RelativeLayout) itemView.findViewById(R.id.rl_video);
            tvTime = (TextView) itemView.findViewById(R.id.tv_mins);
        }
    }

    private String convertTime(double length) {
        int convertDouble = (int) length;
        if (convertDouble < 60) {
            String time = String.format("0:%s", convertDouble);
            return time;
        } else if (convertDouble < 60 * 60) {
            String minutes = String.valueOf(convertDouble / 60);
            String second = String.valueOf(convertDouble - Integer.parseInt(minutes) * 60);
            String time = String.format("%s:%s", minutes, second);
            return time;
        } else {
            String hours = String.valueOf(convertDouble / 3600);
            String minutes = String.valueOf((convertDouble - Integer.parseInt(hours) * 3600) / 60);
            String second = String.valueOf(convertDouble - Integer.parseInt(hours) * 3600 - Integer.parseInt(minutes) * 60);
            String time = String.format("%s:%s:%s", hours, minutes, second);
            return time;
        }
    }
}
