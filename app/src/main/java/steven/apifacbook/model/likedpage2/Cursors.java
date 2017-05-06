package steven.apifacbook.model.likedpage2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TruongNV on 3/26/2017.
 */

public class Cursors {
    @SerializedName("before")
    @Expose
    private String before;
    @SerializedName("after")
    @Expose
    private String after;

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

}
