package steven.apifacbook.model;

import java.util.List;

/**
 * Created by TruongNV on 3/21/2017.
 */

public class InfoFriendList {
    public List<InfoFriendData> getData() {
        return data;
    }

    public void setData(List<InfoFriendData> data) {
        this.data = data;
    }

    public Summary getSummary() {
        return summary;
    }

    public void setSummary(Summary summary) {
        this.summary = summary;
    }

    private List<InfoFriendData> data;
    private Summary summary;

}
