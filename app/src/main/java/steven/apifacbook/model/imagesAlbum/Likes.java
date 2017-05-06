package steven.apifacbook.model.imagesAlbum;

/**
 * Created by TruongNV on 4/4/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Likes {

    @SerializedName("data")
    @Expose
    private List<Datum_> data = null;
    @SerializedName("paging")
    @Expose
    private Paging paging;

    public List<Datum_> getData() {
        return data;
    }

    public void setData(List<Datum_> data) {
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

}
