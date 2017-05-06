package steven.apifacbook.model.LikedPage;

import java.io.Serializable;
import java.util.List;

import steven.apifacbook.model.Paging;

/**
 * Created by TruongNV on 3/26/2017.
 */

public class Data implements Serializable {
    private Paging paging;
    private List<LikedPage> data;

    public List<LikedPage> getList() {
        return data;
    }

    public void setList(List<LikedPage> list) {
        this.data = list;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }


}

