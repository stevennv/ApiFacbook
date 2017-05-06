package steven.apifacbook.model.timeline;

/**
 * Created by TruongNV on 4/21/2017.
 */

public class TimelineData {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreated_time() {
        return created_time;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public String getStatus_type() {
        return status_type;
    }

    public void setStatus_type(String status_type) {
        this.status_type = status_type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String description;
    private String created_time;
    private String status_type;
    private String id;

    public String getFull_picture() {
        return full_picture;
    }

    public void setFull_picture(String full_picture) {
        this.full_picture = full_picture;
    }

    private String full_picture;
}
