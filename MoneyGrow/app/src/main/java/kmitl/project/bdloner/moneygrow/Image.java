package kmitl.project.bdloner.moneygrow;

/**
 * Created by BDLoneR on 18/11/2560.
 */

public class Image {

    private int image_id;
    private String image_title;

    public Image(int image_id, String image_title) {
        this.image_id = image_id;
        this.image_title = image_title;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getImage_title() {
        return image_title;
    }

    public void setImage_title(String image_title) {
        this.image_title = image_title;
    }

}
