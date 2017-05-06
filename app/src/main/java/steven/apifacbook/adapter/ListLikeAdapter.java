package steven.apifacbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import steven.apifacbook.R;
import steven.apifacbook.model.like.Like;
import steven.apifacbook.model.like.LikesData;

/**
 * Created by TruongNV on 3/23/2017.
 */

public class ListLikeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<LikesData> list;

    public ListLikeAdapter(Context context, List<LikesData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_listlike, parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
            LikesData data = list.get(position);
            String url = "http://graph.facebook.com/"
                    + data.getId() + "/picture?type=large";
            Glide.with(context).load(url).into(myViewHolder.imgAvatar);
            myViewHolder.tvName.setText(data.getName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imgAvatar;
        public TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgAvatar = (CircleImageView) itemView.findViewById(R.id.img_avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
