package steven.apifacbook.model.timeline;

import java.util.List;

import steven.apifacbook.model.Paging;

/**
 * Created by TruongNV on 4/21/2017.
 */

public class TimeLine {
    public List<TimelineData> getData() {
        return data;
    }

    public void setData(List<TimelineData> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    private List<TimelineData> data;
    private Paging paging;
}
