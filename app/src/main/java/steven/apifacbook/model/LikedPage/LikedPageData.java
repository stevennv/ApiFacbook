package steven.apifacbook.model.LikedPage;

import steven.apifacbook.model.likedpage2.DataUrl;

/**
 * Created by TruongNV on 3/26/2017.
 */

public class LikedPageData {
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private String id;
    private String name;

    public DataUrl getData() {
        return data;
    }

    public void setData(DataUrl data) {
        this.data = data;
    }

    private DataUrl data;
}
