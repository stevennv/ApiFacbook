package steven.apifacbook.model.imagesAlbum;

/**
 * Created by TruongNV on 4/4/2017.
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
    @SerializedName("previous")
    @Expose
    private String previous;

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

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

}
