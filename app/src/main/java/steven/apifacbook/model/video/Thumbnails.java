package steven.apifacbook.model.video;

import java.util.List;

/**
 * Created by TruongNV on 4/11/2017.
 */

public class Thumbnails {
    public List<ThumbnailData> getData() {
        return data;
    }

    public void setData(List<ThumbnailData> data) {
        this.data = data;
    }

    private List<ThumbnailData> data;
}
