package steven.apifacbook.model;

/**
 * Created by TruongNV on 3/22/2017.
 */

public class Paging {
    private Cursors cursors;
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

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    private String previous;
}
