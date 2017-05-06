package steven.apifacbook.model.abc;

/**
 * Created by TruongNV on 3/26/2017.
 */

public class PictureData {
    public Boolean getIs_silhouette() {
        return is_silhouette;
    }

    public void setIs_silhouette(Boolean is_silhouette) {
        this.is_silhouette = is_silhouette;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private Boolean is_silhouette;
    private String url;
}
