package steven.apifacbook.model.abc;

/**
 * Created by TruongNV on 3/26/2017.
 */

public class LikedPage {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PictureLikedPage getData() {
        return data;
    }

    public void setData(PictureLikedPage data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String name;
    private PictureLikedPage data;
    private String id;
}
