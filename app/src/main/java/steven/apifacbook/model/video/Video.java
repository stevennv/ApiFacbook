package steven.apifacbook.model.video;

import java.util.List;

import steven.apifacbook.model.imagesAlbum.Paging;

/**
 * Created by TruongNV on 4/11/2017.
 */

public class Video {
    public List<VideoData> getData() {
        return data;
    }

    public void setData(List<VideoData> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    private List<VideoData> data;
    private Paging paging;
}
