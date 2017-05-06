package steven.apifacbook.model;

import java.util.List;

/**
 * Created by TruongNV on 3/21/2017.
 */

public class ImageList {
    public List<ImageData> getImages() {
        return images;
    }

    public void setImages(List<ImageData> images) {
        this.images = images;
    }

    private List<ImageData> images;
}
