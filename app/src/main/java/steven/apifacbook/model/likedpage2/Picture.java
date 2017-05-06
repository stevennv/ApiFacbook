package steven.apifacbook.model.likedpage2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TruongNV on 3/26/2017.
 */

public class Picture {
    public DataUrl getData() {
        return data;
    }

    public void setData(DataUrl data) {
        this.data = data;
    }

    @SerializedName("cursors")
    @Expose
    private DataUrl data;
}
