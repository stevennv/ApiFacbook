package steven.apifacbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import steven.apifacbook.R;
import steven.apifacbook.activity.ListFriendActivity;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.likedpage2.Datum;

/**
 * Created by TruongNV on 3/30/2017.
 */

public class FriendAppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<Datum> list;

    public FriendAppAdapter(Context context, List<Datum> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_page_liked, parent, false);
        viewHolder = new FriendAppAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        final Datum data = list.get(position);
        String url = "http://graph.facebook.com/" + data.getId() + "/picture?type=large";
        Log.e("LOG_URL", url);
        Glide.with(context).load(url).into(myViewHolder.imgAvatar);
        myViewHolder.tvName.setText(data.getName());
        myViewHolder.llFriends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ListFriendActivity.class);
                intent.putExtra(Common.friendId, data.getId());
                intent.putExtra("1", Common.ANOTHER);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView imgAvatar;
        public TextView tvName;
        public TextView tvNumberLike;
        public LinearLayout llFriends;

        public MyViewHolder(View itemView) {
            super(itemView);
            llFriends = (LinearLayout) itemView.findViewById(R.id.ll_friendlist);
            imgAvatar = (CircleImageView) itemView.findViewById(R.id.img_avatar_liked_page);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            tvNumberLike = (TextView) itemView.findViewById(R.id.tv_number_likes);
        }
    }
}
