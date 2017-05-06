package steven.apifacbook.model;

/**
 * Created by TruongNV on 3/21/2017.
 */

public class ImageData {
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    private int height;
    private String source;
    private int width;
}
