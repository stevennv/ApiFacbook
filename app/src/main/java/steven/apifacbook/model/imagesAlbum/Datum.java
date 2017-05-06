package steven.apifacbook.model.imagesAlbum;

/**
 * Created by TruongNV on 4/4/2017.
 */

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum  implements Serializable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("likes")
    @Expose
    private Likes likes;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;
    @SerializedName("id")
    @Expose
    private String id;

    protected Datum(Parcel in) {
        name = in.readString();
        id = in.readString();
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
