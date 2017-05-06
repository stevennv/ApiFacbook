package steven.apifacbook.model.download;

/**
 * Created by TruongNV on 4/8/2017.
 */

public class DownLoad {
    public DownLoad(String name, String uriFile) {
        this.name = name;
        this.uriFile = uriFile;
    }

    public DownLoad() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUriFile() {
        return uriFile;
    }

    public void setUriFile(String uriFile) {
        this.uriFile = uriFile;
    }

    private String name;
    private String uriFile;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
}
