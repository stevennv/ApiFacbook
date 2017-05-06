package steven.apifacbook.adapter;

import android.content.Context;
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
import steven.apifacbook.model.imagesAlbum.Image;
import steven.apifacbook.model.timeline.TimelineData;
import steven.apifacbook.ultis.SharePreferenceUtils;

/**
 * Created by TruongNV on 4/21/2017.
 */

public class TimelineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<TimelineData> list;
    private SharePreferenceUtils utils;

    public TimelineAdapter(Context context, List<TimelineData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_timeline, parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        utils = new SharePreferenceUtils(context);
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        TimelineData data = list.get(position);
//        switch (data.getStatus_type()) {
//            case "added_photos":
//                myViewHolder.tvStyleFeed.setText(utils.getNameUser() + " đã thêm ảnh mới");
//                Glide.with(context).load(data.getFull_picture()).into(myViewHolder.imgDetail);
//                break;
//            case "shared_story":
//                myViewHolder.tvStyleFeed.setText(utils.getNameUser() + "đã chia sẻ 1 liên kết");
//                myViewHolder.tvDetail.setText(data.getDescription());
//                Glide.with(context).load(data.getFull_picture()).into(myViewHolder.imgDetail);
//                break;
//            case "mobile_status_update":
//                myViewHolder.tvStyleFeed.setText(utils.getNameUser() + " đã chia sẻ 1 kỷ niệm");
//                break;
//            default:
//                break;
//        }
        if (data.getStatus_type().equals("added_photos")) {
            myViewHolder.tvStyleFeed.setText(utils.getNameUser() + " đã thêm ảnh mới");
            Glide.with(context).load(data.getFull_picture()).into(myViewHolder.imgDetail);
            Log.d("123123123123123", data.getFull_picture() + "     123");
        } else if (data.getStatus_type().equals("shared_story")) {
            myViewHolder.tvStyleFeed.setText(utils.getNameUser() + "đã chia sẻ 1 liên kết");
            myViewHolder.tvDetail.setText(data.getDescription());
            Log.d("LOG_URL", position + "    " + data.getFull_picture());
            Glide.with(context).load(data.getFull_picture()).into(myViewHolder.imgDetail);
        } else {
            myViewHolder.tvStyleFeed.setText(utils.getNameUser() + " đã chia sẻ 1 kỷ niệm");
        }
//        if (data.getFull_picture() == null || data.getFull_picture().equals("")) {
//            myViewHolder.llDetail.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvStyleFeed;
        public TextView tvDetail;
        public ImageView imgDetail;
        public LinearLayout llDetail;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
            tvStyleFeed = (TextView) itemView.findViewById(R.id.tv_style_feeds);
            imgDetail = (ImageView) itemView.findViewById(R.id.img_detail);
            llDetail = (LinearLayout) itemView.findViewById(R.id.ll_details);
        }
    }
}
