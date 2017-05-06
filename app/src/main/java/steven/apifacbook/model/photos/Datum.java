package steven.apifacbook.model.photos;

/**
 * Created by TruongNV on 3/28/2017.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("created_time")
    @Expose
    private String createdTime;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("likes")
    @Expose
    private Likes likes;

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Likes getLikes() {
        return likes;
    }

    public void setLikes(Likes likes) {
        this.likes = likes;
    }

}
