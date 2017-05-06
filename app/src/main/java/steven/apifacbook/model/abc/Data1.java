package steven.apifacbook.model.abc;

import java.util.List;

import steven.apifacbook.model.Paging;

/**
 * Created by TruongNV on 3/26/2017.
 */

public class Data1 {
    public List<Data> getDatas() {
        return datas;
    }

    public void setDatas(List<Data> datas) {
        this.datas = datas;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    private List<Data> datas;
    private Paging paging;
}
