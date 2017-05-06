package steven.apifacbook.model.wall;

/**
 * Created by TruongNV on 3/30/2017.
 */

public class WallData {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getFull_picture() {
        return full_picture;
    }

    public void setFull_picture(String full_picture) {
        this.full_picture = full_picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String message;
    private String story;
    private String full_picture;
    private String id;
}
