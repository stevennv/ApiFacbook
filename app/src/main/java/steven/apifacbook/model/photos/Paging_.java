package steven.apifacbook.model.photos;

/**
 * Created by TruongNV on 3/28/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Paging_ {

    @SerializedName("cursors")
    @Expose
    private Cursors_ cursors;
    @SerializedName("next")
    @Expose
    private String next;

    public Cursors_ getCursors() {
        return cursors;
    }

    public void setCursors(Cursors_ cursors) {
        this.cursors = cursors;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

}
