package steven.apifacbook.model.likedpage2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TruongNV on 3/26/2017.
 */

public class Data {
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("paging")
    @Expose
    private Paging paging;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

}
