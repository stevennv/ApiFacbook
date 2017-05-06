package steven.apifacbook.model.imagesAlbum;

/**
 * Created by TruongNV on 4/4/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image {

    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("width")
    @Expose
    private Integer width;

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

}
