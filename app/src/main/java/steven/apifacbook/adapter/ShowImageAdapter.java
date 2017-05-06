package steven.apifacbook.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.text.method.Touch;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;

import java.util.List;

import steven.apifacbook.R;
import steven.apifacbook.model.imagesAlbum.Datum;
import steven.apifacbook.model.imagesAlbum.Datum_;
import steven.apifacbook.model.imagesAlbum.Image;
import steven.apifacbook.ultis.TouchImageView;

/**
 * Created by TruongNV on 4/11/2017.
 */

public class ShowImageAdapter extends PagerAdapter {
    private Context context;
    private List<Image> list;

    public ShowImageAdapter(Context context, List<Image> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_images_details, container, false);
        TouchImageView tivImage = (TouchImageView) view.findViewById(R.id.tiv_image_detail);
        TouchImageView touchImageView = new TouchImageView(context);
        Glide.with(context).load(list.get(position).getSource()).into(tivImage);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
