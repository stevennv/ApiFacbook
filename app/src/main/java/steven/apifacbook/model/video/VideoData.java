package steven.apifacbook.model.video;

/**
 * Created by TruongNV on 4/11/2017.
 */

public class VideoData {
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnails getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(Thumbnails thumbnails) {
        this.thumbnails = thumbnails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String source;
    private String description;
    private Thumbnails thumbnails;
    private String id;

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    private float length;
}
