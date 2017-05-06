package steven.apifacbook.model.LikedPage;

import java.util.List;

/**
 * Created by TruongNV on 3/26/2017.
 */

public class LikedPage {

    public LikedPageData getLikedPageDatas() {
        return like;
    }

    public void setLikedPageDatas(LikedPageData likedPageDatas) {
        this.like = likedPageDatas;
    }

    private LikedPageData like;
}
