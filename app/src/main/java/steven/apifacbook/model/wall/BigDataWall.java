package steven.apifacbook.model.wall;

import steven.apifacbook.model.Paging;

/**
 * Created by TruongNV on 3/30/2017.
 */

public class BigDataWall {
    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    private Wall wall;
    private Paging paging;
}
