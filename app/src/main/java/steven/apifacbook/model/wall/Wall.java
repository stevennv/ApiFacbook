package steven.apifacbook.model.wall;

import java.util.List;

/**
 * Created by TruongNV on 3/30/2017.
 */

public class Wall {
    public List<WallData> getList() {
        return list;
    }

    public void setList(List<WallData> list) {
        this.list = list;
    }

    private List<WallData> list;
}
