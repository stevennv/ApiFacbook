package steven.apifacbook.model.like;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TruongNV on 4/4/2017.
 */

public class Like {


    @SerializedName("data")
    @Expose
    private List<LikesData> data = null;
    @SerializedName("paging")
    @Expose
    private Paging paging;

    public List<LikesData> getData() {
        return data;
    }

    public void setData(List<LikesData> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }
}
