package steven.apifacbook.model.album;

/**
 * Created by TruongNV on 4/4/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("data")
    @Expose
    private List<PictureData> data ;
    @SerializedName("paging")
    @Expose
    private Paging paging;

    public List<PictureData> getData() {
        return data;
    }

    public void setData(List<PictureData> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

}
