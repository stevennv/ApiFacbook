package steven.apifacbook.model;

import java.io.Serializable;

/**
 * Created by TruongNV on 3/21/2017.
 */

public class UserInfo implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String avatar;
    private String id;

    public UserInfo(String name, String avatar, String id) {
        this.name = name;
        this.avatar = avatar;
        this.id = id;
    }
}
