package steven.apifacbook.model.imagesAlbum;

/**
 * Created by TruongNV on 4/4/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListImage {

    @SerializedName("data")
    @Expose
    private List<Datum> data ;
    @SerializedName("paging")
    @Expose
    private Paging_ paging;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public Paging_ getPaging() {
        return paging;
    }

    public void setPaging(Paging_ paging) {
        this.paging = paging;
    }

}