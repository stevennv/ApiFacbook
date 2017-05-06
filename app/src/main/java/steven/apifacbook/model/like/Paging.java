package steven.apifacbook.model.like;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by TruongNV on 4/4/2017.
 */

public class Paging {
    @SerializedName("cursors")
    @Expose
    private Cursors cursors;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("previous")
    @Expose
    private String previous;

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }


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
