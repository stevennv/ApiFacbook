package steven.apifacbook.model.album;

/**
 * Created by TruongNV on 4/4/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("is_silhouette")
    @Expose
    private Boolean isSilhouette;
    @SerializedName("url")
    @Expose
    private String url;

    public Boolean getIsSilhouette() {
        return isSilhouette;
    }

    public void setIsSilhouette(Boolean isSilhouette) {
        this.isSilhouette = isSilhouette;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
