package steven.apifacbook.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import steven.apifacbook.activity.AlbumListActivity;
import steven.apifacbook.R;
import steven.apifacbook.common.Common;
import steven.apifacbook.model.InfoFriendData;

/**
 * Created by TruongNV on 3/21/2017.
 */

public class ListFriendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<InfoFriendData> list;

    public ListFriendAdapter(Context context, List<InfoFriendData> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_friend, parent, false);
        viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        final InfoFriendData pr = list.get(position);
        Glide.with(context).load(pr.getAvatar()).into(myViewHolder.civAvatar);
        myViewHolder.tvName.setText(pr.getName());
        myViewHolder.llFriendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AlbumListActivity.class);
                intent.putExtra(Common.friendId, pr.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView civAvatar;
        public TextView tvName;
        private LinearLayout llFriendList;

        public MyViewHolder(View itemView) {
            super(itemView);
            civAvatar = (CircleImageView) itemView.findViewById(R.id.civ_avatar);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            llFriendList = (LinearLayout) itemView.findViewById(R.id.ll_friendlist);
        }
    }
}
