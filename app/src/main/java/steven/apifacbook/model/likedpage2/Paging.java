package steven.apifacbook.model.likedpage2;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import steven.apifacbook.model.Cursors;

/**
 * Created by TruongNV on 3/26/2017.
 */

public class Paging {
    @SerializedName("cursors")
    @Expose
    private Cursors cursors;
    @SerializedName("next")
    @Expose
    private String next;

    public Cursors getCursors() {
        return cursors;
    }

    public void setCursors(Cursors cursors) {
        this.cursors = cursors;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }
}
